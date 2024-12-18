package todoapp.todoapp_develop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todoapp.todoapp_develop.domain.Todo;
import todoapp.todoapp_develop.service.TodoService;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    // 속성
    private final TodoService todoSerivce;

    // 일정 생성
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return
    }

    // 전체 일정 조회

    // 선택 일정 조회

    // 일정 수정

    // 일정 삭제


}
