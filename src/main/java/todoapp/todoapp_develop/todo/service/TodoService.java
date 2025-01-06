package todoapp.todoapp_develop.todo.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todoapp.todoapp_develop.global.exception.ErrorCode;
import todoapp.todoapp_develop.global.exception.TodoNotFoundException;
import todoapp.todoapp_develop.global.exception.UserNotExistingException;
import todoapp.todoapp_develop.todo.domain.Todo;
import todoapp.todoapp_develop.user.domain.User;
import todoapp.todoapp_develop.todo.dto.request.TodoRequestDto;
import todoapp.todoapp_develop.todo.dto.response.TodoResponseDto;
import todoapp.todoapp_develop.todo.repository.TodoRepository;
import todoapp.todoapp_develop.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistingException(ErrorCode.USER_NOT_FOUND));

        Todo todo = todoRepository.save(requestDto.toEntity(user));
        return new TodoResponseDto(todo);
    }

    // 전체 일정 조회
    public List<TodoResponseDto> getAllTodos() {
        return todoRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    // 선택 일정 조회
    public TodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND));
        return new TodoResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto, Long userId) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistingException(ErrorCode.USER_NOT_FOUND));

        todo.update(requestDto.getTitle(), requestDto.getContent(), user);
        return new TodoResponseDto(todo);
    }

    @Transactional
    public void deleteTodo(Long id, Long userId) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistingException(ErrorCode.USER_NOT_FOUND));

        todo.delete(user);
        todoRepository.delete(todo);
    }

}
