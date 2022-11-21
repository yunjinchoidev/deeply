package com.server.deeply.user.service;

import com.server.deeply.common.Response;
import com.server.deeply.config.redis.RefreshRedisRepository;
import com.server.deeply.config.redis.RefreshRedisToken;
import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.service.ProfileService;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.mapper.UserMapper;
import com.server.deeply.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshRedisRepository refreshRedisRepository;
    private final TokenProvider tokenProvider;
    private final Response response;
    private final RedisTemplate redisTemplate;
    private final ProfileService profileService;


    /**
     * Spring-Security의 유저 인증 처리 과정중 유저객체를 만드는 과정
     * !! 보통 구글링시 UserDetails 클래스를 따로 만들어서 사용하지만 UserDetails 인터페이스를 구현한
     * User 라는 클래스를 시큐리티가 제공해줘서 굳이 만들어주지 않음
     *
     * @param username userId
     * @return UserDetails (security에서 사용하는 유저 정보를 가진 객체)
     * @throws UsernameNotFoundException userId로 유저를 찾지 못했을 경우 발생하는 에러
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("userId : " + username + " was not found"));

        return User.builder()
                .username(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role("ROLE_USER")
                .build();
    }


    public Long saveUser(UserRequestDto param) {

        Optional<User> findUser = userRepository.findUserByEmail(param.getEmail());

        if (findUser.isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        param.setEncodedPassword(passwordEncoder.encode(param.getPassword()));
        User user = UserMapper.INSTANCE.toEntity(param);

        User saveUser = userRepository.save(user);
        ProfileRequestDto profileRequestDto
                = ProfileRequestDto.builder().user(saveUser).build();

        // 프로필 정보도 같이 저장해주도록 한다.
        profileService.saveProfile(profileRequestDto);

        return saveUser.getId();
    }

    public UserResponseDto findUserByEmail(UserRequestDto param) {
        User user = userRepository.findUserByEmail(param.getEmail()).get();
        UserResponseDto result = UserMapper.INSTANCE.toDto(user);
        return result;
    }

    /*
    * 유저 정보 단일 조회
     */
    public UserResponseDto findById(UserRequestDto param) {
        User user = userRepository.findById(param.getId()).get();
        UserResponseDto result = UserMapper.INSTANCE.toDto(user);
        return result;
    }

    /**
     * 유저 정보 리스트 조회
     * @param param
     * @return
     */
    public Page<UserResponseDto> findAll(UserRequestDto param) {

        Sort sort = Sort.by(param.getOrderBy()).ascending();
        Pageable pageable = PageRequest.of(param.getPage(), param.getPageSize(), sort);

        List<User> userList = userRepository.findAll();

        List<UserResponseDto> userResponseDtos = UserMapper.INSTANCE.toDtoList(userList);

        Page<UserResponseDto> result =
                new PageImpl<>(userResponseDtos, pageable, userList.size());

        return result;
    }

    /**
     * 유저 정보 수정
     * @param param
     * @return
     */
    public UserResponseDto updateUser(UserRequestDto param) {
        User user = UserMapper.INSTANCE.toEntity(param);
        User updateUser = userRepository.save(user);
        UserResponseDto userResponseDto = UserMapper.INSTANCE.toDto(updateUser);
        return userResponseDto;
    }

    /**
     * 로그인
     * @param email
     * @param password
     * @param encoder
     * @return
     */
    public UserResponseDto getByCredentials(final String email,
                                            final String password,
                                            final PasswordEncoder encoder) {

        final User originalUser = userRepository.findByEmail(email);

        UserDetails userDetails = loadUserByUsername(email);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if (!encoder.matches(password, originalUser.getPassword())) {
            throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        // refresh token 발급 및 저장
        log.info("tokenProvider -> createRefreshToken");
        String refreshToken = tokenProvider.createRefreshToken(authentication);
        RefreshRedisToken token = RefreshRedisToken.createToken(email, refreshToken);

        // 기존 토큰이 있으면 수정, 없으면 생성
        refreshRedisRepository.save(token);
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), refreshToken, 604800, TimeUnit.MILLISECONDS);


        // accessToken과 refreshToken 리턴
        final UserResponseDto responseUserDTO = UserResponseDto.builder()
                .id(originalUser.getId())
                .email(email)
                .password(password)
                .username(originalUser.getUsername())
                .role(originalUser.getRole())
                .accessToken(tokenProvider.createAccessToken(authentication))
//                .refreshToken(refreshToken)
                .build();
        return responseUserDTO;
    }


    /**
     * 로그아웃
     * @param logout
     * @return
     */
    public ResponseEntity<?> logout(UserRequestDto logout) {
        // 1. Access Token 검증
        if (!tokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = tokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }
        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = tokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return response.success("로그아웃 되었습니다.");
    }


}
