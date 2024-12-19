package todoapp.todoapp_develop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todoapp.todoapp_develop.domain.Todo;
import todoapp.todoapp_develop.domain.User;
import todoapp.todoapp_develop.dto.requestdto.TodoRequestDto;
import todoapp.todoapp_develop.dto.responsedto.TodoResponseDto;
import todoapp.todoapp_develop.repository.TodoRepository;
import todoapp.todoapp_develop.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //생성자가 자동으로 만들어진다
@Transactional
public class TodoService {
    // 속성
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // 일정 생성
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        User user = userRepository.findById(todoRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTitle(todoRequestDto.getTitle());
        todo.setTodo(todoRequestDto.getTodo());

        Todo savedTodo = todoRepository.save(todo);
        return Todo.todoResponseDto(savedTodo);
    }

    // 전체 일정 조회
    public List<TodoResponseDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(Todo::todoResponseDto)
                .collect(Collectors.toList());
    }

    // 선택 일정 조회
    public TodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
        return Todo.todoResponseDto(todo);
    }

    // 일정 수정
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));

        todo.setTitle(requestDto.getTitle());
        todo.setTodo(requestDto.getTodo());

        Todo updatedTodo = todoRepository.save(todo);
        return Todo.todoResponseDto(updatedTodo);
    }

    // 일정 삭제
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
        todoRepository.deleteById(id);
    }

}
