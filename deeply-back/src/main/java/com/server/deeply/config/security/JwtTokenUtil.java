package com.server.deeply.config.security;

import com.server.deeply.user.jpa.User;
import com.server.deeply.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private static String secret;

    //jwt 토큰에서 사용자 이름 검색.
    public String getEmailFormToken(String token) {
        String encodedKey = Base64.getEncoder().encodeToString(secret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encodedKey.getBytes());
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        String email = String.valueOf(claimsJws.getBody().get("sub"));
        return email;
    }

      //jwt 토큰에서 사용자 이름 검색.
    public String getRoleFromToken(String token) {
        String encodedKey = Base64.getEncoder().encodeToString(secret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encodedKey.getBytes());
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        String role = String.valueOf(claimsJws.getBody().get("role"));
        return role;
    }
    public Long getUserIdFromToken(String token) {
        String encodedKey = Base64.getEncoder().encodeToString(secret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encodedKey.getBytes());
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        Long userId = Long.valueOf(String.valueOf(claimsJws.getBody().get("id")));
        return userId;
    }

    //jwt 토큰에서 만료 날짜 검색.
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    static public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // JWT에서 회원 정보 추출.
    static private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

     static public String parseBearerToken(HttpServletRequest request) {
        // Http 리퀘스트의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
