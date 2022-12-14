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
     * Spring-Security??? ?????? ?????? ?????? ????????? ??????????????? ????????? ??????
     * !! ?????? ???????????? UserDetails ???????????? ?????? ???????????? ??????????????? UserDetails ?????????????????? ?????????
     * User ?????? ???????????? ??????????????? ??????????????? ?????? ??????????????? ??????
     *
     * @param username userId
     * @return UserDetails (security?????? ???????????? ?????? ????????? ?????? ??????)
     * @throws UsernameNotFoundException userId??? ????????? ?????? ????????? ?????? ???????????? ??????
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
            throw new IllegalStateException("?????? ????????? ??????????????????.");
        }

        param.setEncodedPassword(passwordEncoder.encode(param.getPassword()));
        User user = UserMapper.INSTANCE.toEntity(param);

        User saveUser = userRepository.save(user);
//        ProfileRequestDto profileRequestDto
//                = ProfileRequestDto.builder().user(saveUser).build();

        // ????????? ????????? ?????? ?????????????????? ??????.
//        profileService.saveProfile(profileRequestDto);

        return saveUser.getId();
    }

    public UserResponseDto findUserByEmail(UserRequestDto param) {
        User user = userRepository.findUserByEmail(param.getEmail()).get();
        UserResponseDto result = UserMapper.INSTANCE.toDto(user);
        return result;
    }

    /*
    * ?????? ?????? ?????? ??????
     */
    public UserResponseDto findById(UserRequestDto param) {
        User user = userRepository.findById(param.getId()).get();
        UserResponseDto result = UserMapper.INSTANCE.toDto(user);
        return result;
    }

    /**
     * ?????? ?????? ????????? ??????
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
     * ?????? ?????? ??????
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
     * ?????????
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

        // matches ???????????? ????????? ??????????????? ????????? ??????
        if (!encoder.matches(password, originalUser.getPassword())) {
            throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        // refresh token ?????? ??? ??????
        log.info("tokenProvider -> createRefreshToken");
        String refreshToken = tokenProvider.createRefreshToken(authentication);
        RefreshRedisToken token = RefreshRedisToken.createToken(email, refreshToken);

        // ?????? ????????? ????????? ??????, ????????? ??????
        refreshRedisRepository.save(token);
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), refreshToken, 604800, TimeUnit.MILLISECONDS);

        Boolean isAdmin = "ROLE_ADMIN".equals(originalUser.getRole()) ? true : false;

        // accessToken??? refreshToken ??????
        final UserResponseDto responseUserDTO = UserResponseDto.builder()
                .id(originalUser.getId())
                .email(email)
                .password(password)
                .username(originalUser.getUsername())
                .role(originalUser.getRole())
                .isAuth(true)
                .isAdmin(isAdmin)
                .accessToken(tokenProvider.createAccessToken(authentication))
//                .refreshToken(refreshToken)
                .build();
        return responseUserDTO;
    }


    /**
     * ????????????
     * @param logout
     * @return
     */
    public ResponseEntity<?> logout(UserRequestDto logout) {
        // 1. Access Token ??????
        if (!tokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("????????? ???????????????.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token ?????? User email ??? ???????????????.
        Authentication authentication = tokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis ?????? ?????? User email ??? ????????? Refresh Token ??? ????????? ????????? ?????? ??? ?????? ?????? ???????????????.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token ??????
            redisTemplate.delete("RT:" + authentication.getName());
        }
        // 4. ?????? Access Token ???????????? ????????? ?????? BlackList ??? ????????????
        Long expiration = tokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return response.success("???????????? ???????????????.");
    }


}
