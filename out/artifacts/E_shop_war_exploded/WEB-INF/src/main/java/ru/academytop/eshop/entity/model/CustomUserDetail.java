package ru.academytop.eshop.entity.model;

import ru.academytop.eshop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
/**
 * Реализация интерфейса UserDetails для интеграции пользовательских данных
 * с системой Spring Security.
 * Этот класс предоставляет информацию о пользователе для Spring Security.
 */
public class CustomUserDetail implements UserDetails {
    // Сущность User, содержащая данные о пользователе
    private final User user;
    /**
     * Конструктор класса, инициализирующий пользовательские данные.
     *
     * @param user объект сущности User, содержащий информацию о пользователе
     */
    public CustomUserDetail(User user) {
        this.user = user;
    }
    /**
     * Возвращает коллекцию прав (ролей) пользователя.
     * В данном случае, используется роль пользователя для предоставления доступа.
     *
     * @return коллекция прав (ролей) пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
    }
    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    @Override
    public String getUsername() {
        return user.getName();
    }
    /**
     * Проверяет, не истек ли срок действия учетной записи пользователя.
     * В данном случае всегда возвращает true, предполагая, что учетная запись не истекла.
     *
     * @return true, если учетная запись не истекла
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * Проверяет, не заблокирована ли учетная запись пользователя.
     * В данном случае всегда возвращает true, предполагая, что учетная запись не заблокирована.
     *
     * @return true, если учетная запись не заблокирована
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * Проверяет, не истек ли срок действия учетных данных пользователя.
     * В данном случае всегда возвращает true, предполагая, что учетные данные не истекли.
     *
     * @return true, если учетные данные не истекли
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Проверяет, активен ли пользователь.
     * В данном случае всегда возвращает true, предполагая, что пользователь активен.
     *
     * @return true, если пользователь активен
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    /**
     * Возвращает идентификатор пользователя.
     *
     * @return идентификатор пользователя
     */
    public Integer getUserId() {
        return user.getUserId();
    }
    /**
     * Возвращает email пользователя.
     *
     * @return email пользователя
     */
    public String getEmail() {
        return user.getEmail();
    }
    /**
     * Возвращает название роли пользователя.
     *
     * @return название роли пользователя
     */
    public String getRoleName() {
        return user.getRole().getName();
    }
    /**
     * Возвращает баланс пользователя.
     *
     * @return баланс пользователя
     */
    public BigDecimal getBalance() {
        return user.getBalance();
    }
    /**
     * Возвращает дату рождения пользователя.
     *
     * @return дата рождения пользователя
     */
    public LocalDate getBirthDate() {
        return user.getBirthDate();
    }
}
