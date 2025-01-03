package todoapp.todoapp_develop.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoapp.todoapp_develop.todo.dto.request.TodoRequestDto;
import todoapp.todoapp_develop.todo.dto.response.TodoResponseDto;
import todoapp.todoapp_develop.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    // 속성
    private final TodoService todoSerivce;

    /** json
     *  요청------------------
     *  {
     *     "userId": "1",
     *     "title": "sleep",
     *     "todo": "good sleep"
     *  }
     *  ---------------------
     *  응답 -----------------
     *  {
     *     "id": 1,
     *     "username": "minjae",
     *     "title": "sleep",
     *     "todo": "good sleep",
     *     "createdAt": ***,
     *     "updatedAt": ***
     *  }
     */
    // 일정 생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto) {
        return ResponseEntity.ok(todoSerivce.createTodo(requestDto));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        return ResponseEntity.ok(todoSerivce.getAllTodos());
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoSerivce.getTodo(id));
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return ResponseEntity.ok(todoSerivce.updateTodo(id, requestDto));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoSerivce.deleteTodo(id);
        return ResponseEntity.ok("일정이 삭제되었습니다.");
    }

}
