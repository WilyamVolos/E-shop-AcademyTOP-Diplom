package ru.academytop.eshop.repository;

import ru.academytop.eshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для управления сущностями {@link Category}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * и выполнения запросов к базе данных для сущностей {@link Category}. Он наследуется от {@link JpaRepository},
 * что обеспечивает базовую функциональность для работы с сущностями JPA.
 * </p>
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Здесь могут быть добавлены дополнительные методы для выполнения специфических запросов
    // Например, поиск категорий по имени или другие кастомные запросы

}
