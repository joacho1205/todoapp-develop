package todoapp.todoapp_develop.auth.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todoapp.todoapp_develop.auth.dto.request.LoginRequestDto;
import todoapp.todoapp_develop.auth.dto.response.LoginResponseDto;
import todoapp.todoapp_develop.global.util.SessionUtil;
import todoapp.todoapp_develop.user.domain.User;
import todoapp.todoapp_develop.user.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto,
                                                  HttpSession session) {
        User user = userService.login(requestDto);
        SessionUtil.setLoginUserId(session, user.getId());
        return ResponseEntity.ok(new LoginResponseDto(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        SessionUtil.logout(session);
        return ResponseEntity.ok().build();
    }

}