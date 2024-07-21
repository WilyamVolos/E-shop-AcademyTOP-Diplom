package ru.academytop.eshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Сущность пользователя в системе.
 * Хранит информацию о пользователе, включая идентификатор, имя, пароль, электронную почту, дату рождения, баланс и связанные заказы.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    /**
     * Уникальный идентификатор пользователя.
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    /**
     * Имя пользователя.
     * Значение не может быть пустым и должно быть уникальным.
     */
    @NotBlank(message = "Name is required")
    @Column(unique = true)
    private String name;
    /**
     * Пароль пользователя.
     * Значение не может быть пустым и должно быть уникальным.
     */
    @Column(unique = true)
    @NotBlank(message = "Password id is required")
    private String password;
    /**
     * Электронная почта пользователя.
     * Значение должно быть уникальным и соответствовать формату электронной почты.
     */
    @Column(unique = true)
    @Email(message = "Email is not valid")
    private String email;
    /**
     * Дата рождения пользователя.
     * Дата должна быть в прошлом.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthDate;
    /**
     * Баланс пользователя.
     * Значение по умолчанию - '0.00'.
     */
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal balance;
    /**
     * Список заказов пользователя.
     * Связь один ко многим с сущностью Order.
     * Значение загружается немедленно (FetchType.EAGER) и может быть изменено вместе с пользователем (CascadeType.ALL).
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();
    /**
     * Роль пользователя.
     * Связь многие к одному с сущностью Role.
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    /**
     * Переопределение метода equals для сравнения пользователей по идентификатору.
     * Два пользователя считаются равными, если у них одинаковый идентификатор.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        // Сравнение с самим собой
        if (this == o) return true;
        // Проверка типов и нулевых значений
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        // Сравнение идентификаторов
        return userId != null && Objects.equals(userId, user.userId);
    }
    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для пользователя на основе его класса.
     *
     * @return хэш-код пользователя
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }// Генерация хэш-кода на основе класса
}
