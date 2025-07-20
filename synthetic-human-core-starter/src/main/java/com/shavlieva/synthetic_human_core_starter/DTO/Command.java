package com.shavlieva.synthetic_human_core_starter.DTO;

import com.shavlieva.synthetic_human_core_starter.enums.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Команды")
public class Command {
    @Size(min = 1, max = 1000, message = "Должно содержать от 1 до 1000 символов")
    @NotBlank(message = "Не может быть пустыми")
    private String description;

    @NotNull
    private Priority priority;

    @NotBlank
    @Size(min = 5, max = 100, message = "Имя автора должно содержать от 5 до 100 символов")
    private String author;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z", message = "Формат времени должен быть ISO-8601")
    private String time;
}
