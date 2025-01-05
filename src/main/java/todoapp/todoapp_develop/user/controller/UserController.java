package todoapp.todoapp_develop.user.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoapp.todoapp_develop.global.util.SessionUtil;
import todoapp.todoapp_develop.user.dto.request.UserRequestDto;
import todoapp.todoapp_develop.user.dto.response.UserResponseDto;
import todoapp.todoapp_develop.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserRequestDto requestDto,
                                                      HttpSession session) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto, SessionUtil.getLoginUserId(session)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           HttpSession session) {
        userService.deleteUser(id, SessionUtil.getLoginUserId(session));
        return ResponseEntity.ok().build();
    }

}
