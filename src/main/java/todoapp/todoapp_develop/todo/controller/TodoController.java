package todoapp.todoapp_develop.todo.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoapp.todoapp_develop.global.util.SessionUtil;
import todoapp.todoapp_develop.todo.dto.request.TodoRequestDto;
import todoapp.todoapp_develop.todo.dto.response.TodoResponseDto;
import todoapp.todoapp_develop.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto requestDto,
                                                      HttpSession session) {
        return ResponseEntity.ok(todoService.createTodo(requestDto, SessionUtil.getLoginUserId(session)));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id,
                                                      @RequestBody TodoRequestDto requestDto,
                                                      HttpSession session) {
        return ResponseEntity.ok(todoService.updateTodo(id, requestDto, SessionUtil.getLoginUserId(session)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id,
                                           HttpSession session) {
        todoService.deleteTodo(id, SessionUtil.getLoginUserId(session));
        return ResponseEntity.ok().build();
    }

}
