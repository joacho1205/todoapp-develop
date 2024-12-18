package todoapp.todoapp_develop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todoapp.todoapp_develop.dto.RequestDto.LoginRequestDto;
import todoapp.todoapp_develop.dto.ResponseDto.LoginResponseDto;
import todoapp.todoapp_develop.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // 로그인 요청을 처리하는 컨트롤러
    private final UserService userService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpSession session) {
        LoginResponseDto responseDto = userService.login(requestDto);
        session.setAttribute("user", responseDto.getUsername());
        return ResponseEntity.ok(responseDto);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.noContent().build();
    }

}
