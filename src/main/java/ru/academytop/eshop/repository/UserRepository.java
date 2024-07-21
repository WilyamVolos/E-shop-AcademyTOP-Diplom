package ru.academytop.eshop.repository;

import ru.academytop.eshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Репозиторий для управления сущностями {@link User}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * и выполнения запросов к базе данных для сущностей {@link User}. Он наследуется от {@link JpaRepository},
 * что обеспечивает базовую функциональность для работы с сущностями JPA.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Находит пользователя по его имени.
     * <p>
     * Этот метод возвращает объект {@link User}, если пользователь с таким именем найден,
     * или {@link Optional#empty()} если пользователь с таким именем не найден.
     * </p>
     *
     * @param name имя пользователя.
     * @return {@link Optional<User>} содержащий найденного пользователя или пустой Optional, если пользователь не найден.
     */
    Optional<User> findByName(String name);
    /**
     * Находит пользователя по имени и паролю.
     * <p>
     * Этот метод возвращает объект {@link User}, если пользователь с таким именем и паролем найден,
     * или {@link Optional#empty()} если пользователь с таким именем и паролем не найден.
     * </p>
     *
     * @param name     имя пользователя.
     * @param password пароль пользователя.
     * @return {@link Optional<User>} содержащий найденного пользователя или пустой Optional, если пользователь не найден.
     */
    Optional<User> findByNameAndPassword(String name, String password);
    /**
     * Находит пользователя по его электронной почте.
     * <p>
     * Этот метод возвращает объект {@link User}, если пользователь с такой электронной почтой найден,
     * или {@link Optional#empty()} если пользователь с такой электронной почтой не найден.
     * </p>
     *
     * @param email электронная почта пользователя.
     * @return {@link Optional<User>} содержащий найденного пользователя или пустой Optional, если пользователь не найден.
     */
    Optional<User> findByEmail(String email);
    /**
     * Проверяет существует ли пользователь с указанной электронной почтой.
     * <p>
     * Этот метод возвращает true, если пользователь с такой электронной почтой существует,
     * и false, если не существует.
     * </p>
     *
     * @param email электронная почта пользователя.
     * @return true, если пользователь с такой электронной почтой существует, false в противном случае.
     */
    boolean existsByEmail(String email);
}
