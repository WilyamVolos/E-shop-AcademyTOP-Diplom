package ru.academytop.eshop.service.impl;

import ru.academytop.eshop.entity.Category;
import ru.academytop.eshop.entity.Product;
import ru.academytop.eshop.entity.User;
import ru.academytop.eshop.repository.CategoryRepository;
import ru.academytop.eshop.repository.ProductRepository;
import ru.academytop.eshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса для управления категориями и продуктами.
 * <p>
 * Этот класс реализует интерфейс {@link CategoryService} и предоставляет методы для получения всех категорий,
 * поиска продуктов по категориям и управления страницами продуктов.
 * </p>
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    /**
     * Конструктор для инициализации {@link CategoryServiceImpl}.
     *
     * @param categoryRepository репозиторий для работы с категориями.
     * @param productRepository  репозиторий для работы с продуктами.
     */
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /**
     * Получает список всех категорий.
     *
     * @return список всех категорий.
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Получает страницу всех продуктов.
     *
     * @param paging параметры для постраничного отображения.
     * @return страница продуктов.
     */
    @Override
    public Page<Product> findAll(Pageable paging) {
        return productRepository.findAll(paging);
    }

    /**
     * Метод сохранения категории, но в текущей реализации возвращает null.
     *
     * @param category категория, которую нужно сохранить.
     * @return null, так как сохранение не реализовано.
     */
    @Override
    public User save(Category category) {
        return null;
    }

    /**
     * Получает страницу продуктов для заданной категории с учетом постраничного отображения.
     * <p>
     * Метод извлекает продукты для заданной категории, затем создает страницу с учетом текущего номера страницы
     * и размера страницы. Если продукты меньше, чем стартовый элемент, возвращается пустой список.
     * </p>
     *
     * @param categoryId идентификатор категории, для которой нужно найти продукты.
     * @param pageable   параметры постраничного отображения.
     * @return страница продуктов для заданной категории.
     */
    @Override
    public Page findProductsByCategoryId(Integer categoryId, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> products = productRepository.findProductsByCategoryId(categoryId);
        List<Product> list;
        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), products.size());
    }
}
