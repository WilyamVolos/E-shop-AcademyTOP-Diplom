package ru.academytop.eshop.repository;

import ru.academytop.eshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления сущностями {@link Role}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * и выполнения запросов к базе данных для сущностей {@link Role}. Он наследуется от {@link JpaRepository},
 * что обеспечивает базовую функциональность для работы с сущностями JPA.
 * </p>
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Находит роль по её имени.
     * <p>
     * Этот метод выполняет поиск роли по её уникальному имени. Он возвращает роль, если она найдена,
     * или null, если роль с таким именем не существует.
     * </p>
     *
     * @param name имя роли.
     * @return {@link Role} с найденной ролью или null, если роль не найдена.
     */
    Role findByName(String name);

}
