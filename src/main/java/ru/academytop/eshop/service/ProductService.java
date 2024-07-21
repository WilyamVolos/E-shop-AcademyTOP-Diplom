package ru.academytop.eshop.service;

import ru.academytop.eshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * Сервис для управления продуктами.
 * <p>
 * Этот интерфейс описывает методы для получения, поиска и постраничного отображения продуктов в системе.
 * </p>
 */
public interface ProductService {
    /**
     * Возвращает список всех продуктов.
     * <p>
     * Метод возвращает полный список продуктов, доступных в системе.
     * </p>
     *
     * @return список {@link Product} объектов.
     */
    List<Product> findAll();
    /**
     * Находит продукт по его идентификатору.
     * <p>
     * Метод возвращает объект продукта, соответствующий указанному идентификатору, если он существует.
     * </p>
     *
     * @param id идентификатор продукта.
     * @return {@link Optional} содержащий объект {@link Product}, если продукт найден, иначе пустой {@link Optional}.
     */
    Optional<Product> findById(Integer id);
    /**
     * Ищет продукты по названию и описанию.
     * <p>
     * Метод возвращает набор продуктов, у которых название или описание содержат указанный ключ поиска, игнорируя регистр.
     * </p>
     *
     * @param searchKey ключ поиска, который может быть частью названия или описания продукта.
     * @return набор {@link Product} объектов, соответствующих ключу поиска.
     */
    Set<Product> searchProductsByNameAndDescription(String searchKey);
    /**
     * Возвращает постраничный список продуктов.
     * <p>
     * Метод возвращает страницу продуктов, используя переданные параметры {@link Pageable}.
     * </p>
     *
     * @param pageable объект {@link Pageable}, содержащий информацию о странице и размере страницы.
     * @return {@link Page} объектов {@link Product}, соответствующих параметрам постраничного отображения.
     */
    Page<Product> findPaginated(Pageable pageable);

}
