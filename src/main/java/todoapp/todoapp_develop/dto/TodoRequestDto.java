package todoapp.todoapp_develop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoRequestDto {
    @NotBlank(message = "작성 유저명이 비어있습니다.")
    private String username;

    @NotBlank(message = "할일 제목이 비어있습니다.")
    private String title;

    @NotBlank(message = "할일 내용이 비어있습니다.")
    private String todo;

}
