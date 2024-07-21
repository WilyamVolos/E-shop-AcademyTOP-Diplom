package ru.academytop.eshop.repository;

import ru.academytop.eshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * Репозиторий для управления сущностями {@link Product}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * и выполнения запросов к базе данных для сущностей {@link Product}. Он наследуется от {@link JpaRepository},
 * что обеспечивает базовую функциональность для работы с сущностями JPA.
 * </p>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Находит продукт по его имени.
     * <p>
     * Этот метод выполняет поиск продукта по его уникальному имени. Он возвращает опциональный продукт,
     * который может быть пустым, если продукт с таким именем не найден.
     * </p>
     *
     * @param name имя продукта.
     * @return {@link Optional<Product>} с найденным продуктом или пустой, если продукт не найден.
     */
    Optional<Product> findByName(String name);
    /**
     * Находит все продукты, принадлежащие указанной категории.
     * <p>
     * Этот метод выполняет поиск всех продуктов, которые принадлежат категории с указанным идентификатором.
     * Он возвращает список продуктов, которые связаны с данной категорией.
     * </p>
     *
     * @param categoryId идентификатор категории, к которой принадлежат продукты.
     * @return список {@link Product} для указанной категории.
     */
    @Query("SELECT p FROM Product p WHERE p.category.categoryId =:categoryId")
    List<Product> findProductsByCategoryId(Integer categoryId);
    /**
     * Находит продукты, название или описание которых содержат указанный поисковый ключ.
     * <p>
     * Этот метод выполняет поиск продуктов, название или описание которых содержат указанный поисковый ключ.
     * Поиск выполняется без учета регистра. Возвращается множество найденных продуктов.
     * </p>
     *
     * @param searchKeyName ключ для поиска в названии продукта.
     * @param searchKeyDesc ключ для поиска в описании продукта.
     * @return множество {@link Product}, соответствующих поисковым критериям.
     */
    Set<Product> findProductsByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String searchKeyName, String searchKeyDesc);
    /**
     * Находит продукт по его идентификатору.
     * <p>
     * Этот метод выполняет поиск продукта по уникальному идентификатору продукта. Он возвращает опциональный продукт,
     * который может быть пустым, если продукт с таким идентификатором не найден.
     * </p>
     *
     * @param productId уникальный идентификатор продукта.
     * @return {@link Optional<Product>} с найденным продуктом или пустой, если продукт не найден.
     */
    Optional<Product> findProductByProductId(Integer productId);
    /**
     * Находит все продукты, принадлежащие указанной категории, с поддержкой постраничного вывода.
     * <p>
     * Этот метод выполняет поиск всех продуктов в категории с указанным идентификатором, с поддержкой постраничного вывода.
     * Возвращается страница продуктов, соответствующих критериям поиска и параметрам пагинации.
     * </p>
     *
     * @param categoryId идентификатор категории, к которой принадлежат продукты.
     * @param pageable объект {@link Pageable} для поддержки постраничного вывода.
     * @return объект {@link Page<Product>} с найденными продуктами и информацией о постраничном выводе.
     */
    Page<Product> findAllByCategory_CategoryId(Integer categoryId, Pageable pageable);

}
