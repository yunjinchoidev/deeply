package com.server.deeply.config.security;

import com.server.deeply.user.service.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;
    private final JwtTokenUtil jwtTokenUtil;
//    private final UserServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 리퀘스트에서 토큰 가져오기.
            String token = parseBearerToken(request);
            String refreshToken = resolveRefreshToken(request);
            log.info("Filter is running...");
            // 토큰 검사하기. JWT이므로 인가 서버에 요청 하지 않고도 검증 가능.
            if (token != null && !token.equalsIgnoreCase("null")) {
                String isLogout = (String) redisTemplate.opsForValue().get(token);
                if (ObjectUtils.isEmpty(isLogout)) {
                    // userId 가져오기. 위조 된 경우 예외 처리 된다.
                    //                    boolean validateToken = tokenProvider.validateToken(token);
                    //                String userId = b;

                    // 어세스 토큰이 유효한 상황
                    if (tokenProvider.validateToken(token)) {
//                        log.info("Authenticated user ID : " + );
                        // 인증 완료; SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
                        AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                tokenProvider.validateToken(token), // 인증된 사용자의 정보. 문자열이 아니어도 아무거나 넣을 수 있다.
                                null, //
                                AuthorityUtils.NO_AUTHORITIES
                        );
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                        securityContext.setAuthentication(authentication);
                        SecurityContextHolder.setContext(securityContext);
                    } // 어세스 토큰이 만료된 상황 | 리프레시 토큰 또한 존재하는 상황
                    else if (!tokenProvider.validateToken(token) && refreshToken != null) {
                        // 재발급 후, 컨텍스트에 다시 넣기
                        /// 리프레시 토큰 검증
                        boolean validateRefreshToken = tokenProvider.validateToken(refreshToken);
                        /// 리프레시 토큰 저장소 존재유무 확인
                        boolean isRefreshToken = tokenProvider.existsRefreshToken(refreshToken);
                        if (validateRefreshToken && isRefreshToken) {
                            /// 리프레시 토큰으로 이메일 정보 가져오기
                            String email = jwtTokenUtil.getEmailFormToken(refreshToken);
                            /// 이메일로 권한정보 받아오기
                            String role = jwtTokenUtil.getRoleFromToken(email);

                            Authentication authentication = tokenProvider.getAuthentication(token);

                            /// 토큰 발급
                            String newAccessToken = tokenProvider.createAccessToken(authentication);
                            /// 헤더에 어세스 토큰 추가
                            tokenProvider.setHeaderAccessToken(response, newAccessToken);
//                            / 컨텍스트에 넣기
                            this.setAuthentication(newAccessToken);
                        }
                    }


                } else {
                    throw new IllegalStateException("이미 로그아웃된 계정입니다.");
                }
            }
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", e);
            log.info("ExpiredJwtException : {}", e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
            request.setAttribute("exception", e);
            log.info("jwtException : {}", e.getMessage());
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        // Http 리퀘스트의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
    public String resolveRefreshToken(HttpServletRequest request) {
        if (request.getHeader("refreshToken") != null)
            return request.getHeader("refreshToken").substring(7);
        return null;
    }

    // SecurityContext 에 Authentication 객체를 저장합니다.
    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = tokenProvider.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
