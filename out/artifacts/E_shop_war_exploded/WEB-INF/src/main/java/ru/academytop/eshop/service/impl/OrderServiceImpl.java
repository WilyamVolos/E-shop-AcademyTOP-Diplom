package ru.academytop.eshop.service.impl;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.entity.Cart;
import ru.academytop.eshop.entity.Order;
import ru.academytop.eshop.entity.User;
import ru.academytop.eshop.entity.converter.CartConverter;
import ru.academytop.eshop.repository.OrderRepository;
import ru.academytop.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
/**
 * Реализация сервиса для управления заказами.
 * <p>
 * Этот класс реализует интерфейс {@link OrderService} и предоставляет методы для создания и чтения заказов.
 * </p>
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartConverter cartConverter;
    /**
     * Конструктор для инициализации {@link OrderServiceImpl}.
     *
     * @param orderRepository репозиторий для работы с заказами.
     * @param cartConverter преобразователь для конвертации корзины в сущность заказа.
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartConverter cartConverter) {
        this.orderRepository = orderRepository;
        this.cartConverter = cartConverter;
    }
    /**
     * Сохраняет заказ, используя данные из корзины и идентификатор пользователя.
     * <p>
     * Метод преобразует {@link CartDto} в {@link Cart}, затем создает {@link Order} и сохраняет его в базе данных.
     * </p>
     *
     * @param cartDto данные корзины, которые нужно сохранить в заказе.
     * @param userId идентификатор пользователя, который создает заказ.
     * @return сохраненный {@link Order}.
     */
    @Override
    public Order save(CartDto cartDto, Integer userId) {
        // Преобразуем CartDto в Cart
        Cart cart = cartConverter.convertToEntity(cartDto);
        // Создаем User объект с заданным userId
        User user = User.builder()
                .userId(userId)
                .build();
        // Создаем Order объект с данными из корзины и пользователя
        Order order = Order.builder()
                .price(BigDecimal.valueOf(cart.getTotalPrice()))
                .user(user)
                .products(cart.getProducts())
                .build();
        // Сохраняем и возвращаем сохраненный заказ
        return orderRepository.saveAndFlush(order);
    }
    /**
     * Находит все заказы для указанного пользователя.
     *
     * @param userId идентификатор пользователя, для которого нужно найти заказы.
     * @return список {@link Order} для указанного пользователя.
     */
    @Override
    public List<Order> readOrders(Integer userId) {
        // Создаем User объект с заданным userId
        User user = User.builder()
                .userId(userId)
                .build();
        // Находим и возвращаем все заказы для указанного пользователя
        return orderRepository.findAllByUser(user);
    }
    /**
     * Находит заказ по идентификатору заказа.
     *
     * @param orderId идентификатор заказа, который нужно найти.
     * @return {@link Order} с указанным идентификатором заказа.
     */
    @Override
    public Order read(Integer orderId) {
        // Находим и возвращаем заказ по его идентификатору
        return orderRepository.findByOrderId(orderId);
    }

}
