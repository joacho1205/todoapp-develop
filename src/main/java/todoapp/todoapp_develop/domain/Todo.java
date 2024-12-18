package todoapp.todoapp_develop.domain;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends BaseEntity {
    // 일정 데이터를 관리하기 위한 엔티티

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일정 ID (고유값)
    @NotBlank(message = "작성 유저명이 비어있습니다.")
    private String username; // 작성 유저명
    @NotBlank(message = "할일 제목이 비어있습니다.")
    private String title; // 할일 제목
    @NotBlank(message = "할일 내용이 비어있습니다.")
    private String todo; // 할일 내용

}
