package ru.academytop.eshop.service.impl;

import ru.academytop.eshop.entity.Product;
import ru.academytop.eshop.repository.ProductRepository;
import ru.academytop.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * Реализация сервиса для управления продуктами.
 * <p>
 * Этот класс реализует интерфейс {@link ProductService} и предоставляет методы для поиска, получения и пагинации продуктов.
 * </p>
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    /**
     * Конструктор для инициализации {@link ProductServiceImpl}.
     *
     * @param productRepository репозиторий для работы с продуктами.
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Находит и возвращает все продукты.
     *
     * @return список всех {@link Product}.
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    /**
     * Находит продукт по идентификатору.
     *
     * @param id идентификатор продукта, который нужно найти.
     * @return {@link Optional<Product>} с найденным продуктом, если он существует.
     */
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    /**
     * Ищет продукты по имени или описанию, используя ключевое слово для поиска.
     * <p>
     * Метод выполняет поиск продуктов, в имени или описании которых содержится указанное ключевое слово (не учитывая регистр).
     * </p>
     *
     * @param searchKey ключевое слово для поиска в имени и описании продуктов.
     * @return множество {@link Product}, соответствующих критериям поиска.
     */
    @Override
    public Set<Product> searchProductsByNameAndDescription(String searchKey) {
        return productRepository.findProductsByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey, searchKey);
    }
    /**
     * Находит продукты с поддержкой пагинации.
     * <p>
     * Метод разбивает все продукты на страницы, используя информацию о текущей странице и размере страницы.
     * </p>
     *
     * @param pageable объект {@link Pageable}, содержащий информацию о текущей странице и размере страницы.
     * @return объект {@link Page<Product>} с продуктами для текущей страницы.
     */
    @Override
    public Page<Product> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        // Получаем все продукты из репозитория
        List<Product> products = productRepository.findAll();
        List<Product> list;
        // Проверяем, есть ли продукты на текущей странице
        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }
        // Создаем и возвращаем объект Page с продуктами для текущей страницы
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), products.size());
    }
}
