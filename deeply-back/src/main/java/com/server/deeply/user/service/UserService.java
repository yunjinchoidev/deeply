package com.server.deeply.user.service;

import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.mapper.UserMapper;
import com.server.deeply.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long saveUser(UserRequestDto param) {
        Optional<User> findUser = userRepository.findUserByEmail(param.getEmail());

        if (findUser.isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        param.setEncodedPassword(passwordEncoder.encode(param.getPassword()));
        User user = UserMapper.INSTANCE.toEntity(param);

        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    public Optional<User> findUserByEmail(UserRequestDto param) {
        Optional<User> user = userRepository.findUserByEmail(param.getEmail());
        return user;
    }

    public Optional<User> findUserById(UserRequestDto param) {
        Optional<User> user = userRepository.findById(param.getId());
        return user;
    }

    public User getByCredentials(final String email,
                                 final String password,
                                 final PasswordEncoder encoder) {

        final User originalUser = userRepository.findByEmail(email);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }
}
