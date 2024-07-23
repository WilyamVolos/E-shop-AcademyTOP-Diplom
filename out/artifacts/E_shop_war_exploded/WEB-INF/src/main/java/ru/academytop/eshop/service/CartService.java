package ru.academytop.eshop.service;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.dto.ProductDto;

import java.util.List;
/**
 * Сервис для управления корзиной покупок.
 * <p>
 * Этот интерфейс описывает методы для работы с корзиной покупок, включая добавление и удаление товаров,
 * очистку корзины и вычисление общей стоимости товаров в корзине.
 * </p>
 */
public interface CartService {
    /**
     * Добавляет продукт в корзину.
     * <p>
     * Если продукт с данным идентификатором найден, он добавляется в корзину. Обновленная корзина возвращается.
     * </p>
     *
     * @param productId идентификатор продукта для добавления.
     * @param cartDto объект {@link CartDto} представляющий текущую корзину покупок.
     * @return обновленный {@link CartDto} объект с добавленным продуктом.
     */
    CartDto addProduct(Integer productId, CartDto cartDto);
    /**
     * Удаляет продукт из корзины.
     * <p>
     * Если продукт с данным идентификатором присутствует в корзине, он удаляется. Обновленная корзина возвращается.
     * </p>
     *
     * @param productId идентификатор продукта для удаления.
     * @param cartDto объект {@link CartDto} представляющий текущую корзину покупок.
     * @return обновленный {@link CartDto} объект с удаленным продуктом.
     */
    CartDto removeProduct(Integer productId, CartDto cartDto);
    /**
     * Очищает корзину.
     * <p>
     * Все продукты удаляются из корзины, и корзина возвращается в пустом состоянии.
     * </p>
     *
     * @param cartDto объект {@link CartDto} представляющий текущую корзину покупок.
     * @return обновленный {@link CartDto} объект с пустой корзиной.
     */
    CartDto clear(CartDto cartDto);
    /**
     * Вычисляет общую стоимость всех товаров в корзине.
     * <p>
     * Суммирует цены всех продуктов в корзине и возвращает общую стоимость.
     * </p>
     *
     * @param productsDto список {@link ProductDto} объектов представляющих продукты в корзине.
     * @return общая стоимость товаров в корзине.
     */
    double calculateTotalPrice(List<ProductDto> productsDto);

}
