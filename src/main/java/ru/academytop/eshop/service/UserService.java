package ru.academytop.eshop.service;

import ru.academytop.eshop.dto.RegistrationFormDto;
import ru.academytop.eshop.entity.User;

import java.util.List;
/**
 * Сервис для управления пользователями.
 * <p>
 * Этот интерфейс описывает методы для получения списка всех пользователей и регистрации нового пользователя в системе.
 * </p>
 */
public interface UserService {
    /**
     * Возвращает список всех пользователей.
     * <p>
     * Метод возвращает полный список пользователей, зарегистрированных в системе.
     * </p>
     *
     * @return список {@link User} объектов.
     */
    List<User> findAll();
    /**
     * Регистрирует нового пользователя.
     * <p>
     * Метод создает и сохраняет нового пользователя в системе на основе данных, предоставленных в {@link RegistrationFormDto}.
     * </p>
     *
     * @param registrationFormDto объект, содержащий данные для регистрации нового пользователя.
     */
    void register(RegistrationFormDto registrationFormDto);

}
