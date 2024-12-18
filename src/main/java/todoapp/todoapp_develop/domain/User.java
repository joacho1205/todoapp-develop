package todoapp.todoapp_develop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import todoapp.todoapp_develop.dto.UserResponseDto;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    // 유저 데이터를 관리하기 위한 엔티티

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 고유 식별자
    @NotBlank(message = "유저명이 비어있습니다.")
    private String username; // 유저명
    @NotBlank(message = "이메일이 비어있습니다.")
    private String email; // 이메일
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 작성일

    // Entity -> ResponseDto 변환 메서드
    public static UserResponseDto userResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
