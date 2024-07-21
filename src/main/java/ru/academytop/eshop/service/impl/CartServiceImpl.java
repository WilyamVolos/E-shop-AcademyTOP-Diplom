package ru.academytop.eshop.service.impl;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.dto.ProductDto;
import ru.academytop.eshop.entity.Product;
import ru.academytop.eshop.entity.converter.ProductConverter;
import ru.academytop.eshop.repository.ProductRepository;
import ru.academytop.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Реализация сервиса для управления корзиной покупок.
 * <p>
 * Этот класс реализует интерфейс {@link CartService} и предоставляет методы для добавления, удаления
 * и очистки товаров в корзине. Также содержит метод для расчета общей стоимости корзины.
 * </p>
 */
@Service
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    /**
     * Конструктор для инициализации {@link CartServiceImpl}.
     *
     * @param productRepository репозиторий для работы с продуктами.
     * @param productConverter конвертер для преобразования продуктов в DTO и обратно.
     */
    @Autowired
    public CartServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }
    /**
     * Добавляет продукт в корзину.
     * <p>
     * Если идентификатор продукта и корзина не равны null, метод находит продукт по идентификатору,
     * преобразует его в {@link ProductDto} и добавляет в список продуктов корзины. Затем обновляет
     * корзину, устанавливая новую общую стоимость и количество продуктов.
     * </p>
     *
     * @param productId идентификатор продукта, который нужно добавить.
     * @param cartDto объект корзины, в которую нужно добавить продукт.
     * @return обновленная корзина.
     */
    @Override
    public CartDto addProduct(Integer productId, CartDto cartDto) {
        List<ProductDto> products = new ArrayList<>();
        if (productId != null && cartDto != null) {
            Optional<Product> product = productRepository.findProductByProductId(productId);
            ProductDto productDto = productConverter.convertToDto(product.orElse(null));
            products = cartDto.getProducts();
            products.add(productDto);
            cartDto.setProducts(products);
        }
        return CartDto.builder()
                .products(products)
                .totalPrice(calculateTotalPrice(products))
                .quantity(products.size())
                .build();
    }
    /**
     * Удаляет продукт из корзины.
     * <p>
     * Метод удаляет продукт с заданным идентификатором из списка продуктов корзины, если такой продукт присутствует.
     * Затем обновляет корзину, устанавливая новую общую стоимость и количество продуктов.
     * </p>
     *
     * @param productId идентификатор продукта, который нужно удалить.
     * @param cartDto объект корзины, из которой нужно удалить продукт.
     * @return обновленная корзина.
     */
    @Override
    public CartDto removeProduct(Integer productId, CartDto cartDto) {
        List<ProductDto> products = cartDto.getProducts();
        products.removeIf(product -> product.getProductId().equals(productId));
        return CartDto.builder()
                .products(products)
                .totalPrice(calculateTotalPrice(products))
                .quantity(products.size())
                .build();
    }
    /**
     * Очищает корзину.
     * <p>
     * Метод очищает список продуктов в корзине и обновляет корзину с нулевой общей стоимостью и количеством продуктов.
     * </p>
     *
     * @param cartDto объект корзины, которую нужно очистить.
     * @return обновленная (очищенная) корзина.
     */
    @Override
    public CartDto clear(CartDto cartDto) {
        List<ProductDto> productsDto = cartDto.getProducts();
        productsDto.clear();
        return CartDto.builder()
                .products(new ArrayList<>())
                .totalPrice(0.0)
                .quantity(0)
                .build();
    }
    /**
     * Вычисляет общую стоимость продуктов в корзине.
     * <p>
     * Метод суммирует цены всех продуктов в списке и возвращает итоговую сумму.
     * </p>
     *
     * @param productsDto список продуктов {@link ProductDto} для расчета общей стоимости.
     * @return общая стоимость продуктов.
     */
    @Override
    public double calculateTotalPrice(List<ProductDto> productsDto) {
        return productsDto.stream()
                .mapToDouble(ProductDto::getPrice)
                .sum();
    }
}
