package ru.academytop.eshop.service;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.entity.Order;

import java.util.List;
/**
 * Сервис для управления заказами.
 * <p>
 * Этот интерфейс описывает методы для создания, чтения и получения заказов в системе.
 * </p>
 */
public interface OrderService {
    /**
     * Сохраняет новый заказ, используя информацию из корзины и идентификатор пользователя.
     * <p>
     * Создает новый заказ на основе данных из {@link CartDto} и связывает его с указанным пользователем.
     * </p>
     *
     * @param cartDto объект {@link CartDto}, содержащий информацию о товарах в корзине.
     * @param userId идентификатор пользователя, который размещает заказ.
     * @return сохраненный {@link Order} объект.
     */
    Order save(CartDto cartDto, Integer userId);
    /**
     * Находит все заказы пользователя.
     * <p>
     * Возвращает список всех заказов, сделанных указанным пользователем.
     * </p>
     *
     * @param userId идентификатор пользователя, чьи заказы нужно найти.
     * @return список {@link Order} объектов для указанного пользователя.
     */
    List<Order> readOrders(Integer userId);
    /**
     * Находит заказ по его идентификатору.
     * <p>
     * Возвращает подробности заказа для указанного идентификатора.
     * </p>
     *
     * @param orderId идентификатор заказа, который нужно найти.
     * @return {@link Order} объект с указанным идентификатором.
     */
    Order read (Integer orderId);
}
