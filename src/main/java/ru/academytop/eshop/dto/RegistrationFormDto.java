package ru.academytop.eshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
/**
 * DTO (Data Transfer Object) для представления формы регистрации пользователя.
 * Содержит данные, необходимые для регистрации нового пользователя.
 */
@Getter
@Builder
public class RegistrationFormDto {
    /**
     * Имя пользователя.
     * Не может быть пустым и должно содержать от 2 до 20 символов.
     */
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;
    /**
     * Пароль пользователя.
     * Не может быть пустым и должен содержать от 2 до 20 символов.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 2, max = 20, message = "Password id is required")
    private String password;
    /**
     * Электронная почта пользователя.
     * Не может быть null и должна быть корректно отформатирована как email.
     */
    @NotNull
    @Email(message = "Email is not valid")
    private String email;
    /**
     * Дата рождения пользователя.
     * Должна быть в прошлом.
     * Формат даты: "yyyy-MM-dd".
     */
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
