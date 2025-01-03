package todoapp.todoapp_develop.todo.domain;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import todoapp.todoapp_develop.user.domain.User;
import todoapp.todoapp_develop.todo.dto.response.TodoResponseDto;
import todoapp.todoapp_develop.global.domain.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends BaseEntity {
    // 일정 데이터를 관리하기 위한 엔티티

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일정 ID (고유값)
    @NotBlank(message = "할일 제목이 비어있습니다.")
    private String title; // 할일 제목
    @NotBlank(message = "할일 내용이 비어있습니다.")
    private String todo; // 할일 내용
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 작성 유저

    // Entity -> ResponseDto 변환 메서드
    public static TodoResponseDto todoResponseDto(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .username(todo.getUser().getUsername())
                .title(todo.getTitle())
                .todo(todo.getTodo())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}
