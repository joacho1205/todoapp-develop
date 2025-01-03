package todoapp.todoapp_develop.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoapp.todoapp_develop.user.dto.request.UserRequestDto;
import todoapp.todoapp_develop.user.dto.response.UserResponseDto;
import todoapp.todoapp_develop.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    // 속성
    private final UserService userService;

    /** json
     *  요청------------------
     *  {
     *     "username": "minjae",
     *     "password": "0000",
     *     "email": "minjae@example.com"
     *  }
     *  ---------------------
     *  응답 -----------------
     *  {
     *     "id": 1,
     *     "username": "minjae",
     *     "email": "minjae@example.com",
     *     "createdAt": ***
     *  }
     */
    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.createUser(requestDto));
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 선택 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    // 유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("유저가 삭제되었습니다.");
    }


}
