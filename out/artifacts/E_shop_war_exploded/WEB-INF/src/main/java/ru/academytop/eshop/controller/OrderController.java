package ru.academytop.eshop.controller;

import ru.academytop.eshop.entity.User;
import ru.academytop.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

/**
 * Контроллер для управления заказами.
 */
@Controller
@SessionAttributes({"user"})
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    /**
     * Конструктор, использующий Dependency Injection для внедрения зависимости OrderService.
     *
     * @param orderService сервис для работы с заказами
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Инициализирует объект User в сессии.
     *
     * @return новый объект User
     */
    @ModelAttribute("user")
    public User User() {
        return new User();
    }

    /**
     * Обработчик GET-запросов для отображения истории заказов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return объект ModelAndView с данными для отображения страницы истории заказов
     */
    @GetMapping("/read-history/{userId}")
    public ModelAndView showOrdersHistory(@PathVariable String userId) {
        ModelMap modelParams = new ModelMap();
        if (userId != null) {
            Integer id = Integer.parseInt(userId);
            modelParams.addAttribute("orders", orderService.readOrders(id));
        } else {
            return new ModelAndView(PageName.HOME_PAGE, modelParams);
        }
        return new ModelAndView(PageName.ORDER_HISTORY_PAGE, modelParams);
    }

    /**
     * Обработчик GET-запросов для отображения конкретного заказа.
     *
     * @param orderId идентификатор заказа
     * @return объект ModelAndView с данными для отображения страницы заказа
     */
    @GetMapping("/read/{orderId}")
    public ModelAndView showOrder(@PathVariable String orderId) {
        ModelMap modelParams = new ModelMap();
        if (orderId != null) {
            Integer id = Integer.parseInt(orderId);
            modelParams.addAttribute("order", orderService.read(id));
        } else {
            return new ModelAndView(PageName.HOME_PAGE, modelParams);
        }
        return new ModelAndView(PageName.ORDER_PAGE, modelParams);
    }
}