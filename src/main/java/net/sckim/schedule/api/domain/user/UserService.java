package net.sckim.schedule.api.domain.user;

import net.sckim.schedule.api.domain.security.PasswordEncoder;
import net.sckim.schedule.api.domain.user.dto.LoginResponse;
import net.sckim.schedule.api.domain.user.dto.UserResponse;
import net.sckim.schedule.api.domain.user.entity.User;
import net.sckim.schedule.api.domain.user.exception.PasswordMismatchedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createUser(String name, String email, String password) {
        final String encodedPassword = passwordEncoder.encode(password);

        final User newUser = User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .build();

        final User createdUser = userRepository.save(newUser);

        return UserResponse.of(createdUser);
    }

    public UserResponse getUser(Long userId) {
        final User user = getUserOrElseThrow(userId);
        return UserResponse.of(user);
    }

    private User getUserOrElseThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found. id = " + userId));
    }

    public List<UserResponse> getAllUsers() {
        final List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(UserResponse::of)
                .toList();
    }

    @Transactional
    public UserResponse editUser(Long userId, String name, String email) {
        final User user = getUserOrElseThrow(userId);

        user.edit(name, email);
        userRepository.save(user);

        return UserResponse.of(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        // 일정 삭제와 다르게 userId로 바로 삭제함
        // 이 경우는 미존재 userId로 요청하더라도 아무런 에러 없이 정상 처리 됨
        // 미존재 데이터에 대한 예외처리나 확인이 필요 없다면 이렇게 처리해도 무방하다.
        userRepository.deleteById(userId);
    }

    public LoginResponse login(String email, String password) {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found. email = " + email));


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMismatchedException("Password is incorrect. email = " + email);
        }

        return LoginResponse.of(user);
    }
}