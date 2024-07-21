package ru.academytop.eshop.service;

import ru.academytop.eshop.entity.Category;
import ru.academytop.eshop.entity.Product;
import ru.academytop.eshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * Сервис для управления категориями товаров.
 * <p>
 * Этот интерфейс описывает методы для работы с категориями, включая поиск всех категорий,
 * получение товаров по категориям, а также сохранение и управление категориями.
 * </p>
 */
public interface CategoryService {
    /**
     * Находит все категории товаров.
     * <p>
     * Возвращает список всех категорий, доступных в системе.
     * </p>
     *
     * @return список всех {@link Category} объектов.
     */
    List<Category> findAll();
    /**
     * Находит все продукты с поддержкой постраничного отображения.
     * <p>
     * Возвращает страницу продуктов с учетом указанных параметров постраничного отображения.
     * </p>
     *
     * @param paging объект {@link Pageable} содержащий параметры постраничного отображения.
     * @return {@link Page} содержащий страницу {@link Product} объектов.
     */
    Page<Product> findAll(Pageable paging);
    /**
     * Сохраняет новую категорию.
     * <p>
     * Сохраняет переданную категорию в системе. Обратите внимание, что тип возвращаемого значения User
     * может быть ошибочен и в реальной системе должен возвращать тип Category или другой подходящий тип.
     * </p>
     *
     * @param category объект {@link Category} для сохранения.
     * @return сохраненный {@link User} объект (возможно ошибка в типе возвращаемого значения, лучше {@link Category}).
     */
    User save(Category category);
    /**
     * Находит продукты по идентификатору категории с поддержкой постраничного отображения.
     * <p>
     * Возвращает страницу продуктов, которые принадлежат указанной категории, с учетом параметров
     * постраничного отображения.
     * </p>
     *
     * @param categoryId идентификатор категории, для которой нужно найти продукты.
     * @param pageable объект {@link Pageable} содержащий параметры постраничного отображения.
     * @return {@link Page} содержащий страницу {@link Product} объектов.
     */
    Page findProductsByCategoryId(Integer categoryId, Pageable pageable);

}
