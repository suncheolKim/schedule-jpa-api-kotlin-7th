package net.sckim.schedule.api.domain.user;

import net.sckim.schedule.api.domain.user.dto.CreateUserRequest;
import net.sckim.schedule.api.domain.user.dto.EditUserRequest;
import net.sckim.schedule.api.domain.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request.getName(), request.getEmail(), request.getPassword());
    }

    @GetMapping("/users/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody EditUserRequest request) {
        return userService.editUser(userId, request.getName(), request.getEmail());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok()
                .build();
    }
}
