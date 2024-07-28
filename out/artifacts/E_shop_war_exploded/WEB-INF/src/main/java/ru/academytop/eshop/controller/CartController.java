package ru.academytop.eshop.controllers;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.entity.Order;
import ru.academytop.eshop.entity.User;
import ru.academytop.eshop.service.CartService;
import ru.academytop.eshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

/**
 * Контроллер для управления корзиной покупок.
 */

@Controller
@SessionAttributes({"cartDto", "user"})
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    /**
     * Конструктор, использующий Dependency Injection для внедрения зависимостей
     * CartService и OrderService.
     *
     * @param cartService  сервис для работы с корзиной покупок
     * @param orderService сервис для работы с заказами
     */

    @Autowired
    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    /**
     * Инициализирует объект CartDto в сессии.
     *
     * @return новый объект CartDto
     */
    @ModelAttribute("cartDto")
    public CartDto initializeCartSessionObject() {
        return new CartDto();
    }

    /**
     * Инициализирует объект User в сессии.
     *
     * @return новый объект User
     */
    @ModelAttribute("user")
    public User initializeUserSessionObject() {
        return new User();
    }

    /**
     * Добавляет продукт в корзину.
     *
     * @param productId идентификатор продукта
     * @param cartDto   объект корзины покупок, полученный из сессии
     * @return объект ModelAndView с обновленной корзиной или главной страницей, если productId отсутствует
     */

    @PostMapping("/add/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") String productId,
                                         @Valid @ModelAttribute CartDto cartDto) {
        ModelMap modelParams = new ModelMap();
        if (productId != null) {
            Integer id = Integer.parseInt(productId);
            modelParams.addAttribute("cartDto", cartService.addProduct(id, cartDto));
            return new ModelAndView(PageName.CART_PAGE, modelParams);
        }
        return new ModelAndView(PageName.HOME_PAGE, modelParams);
    }

    /**
     * Удаляет продукт из корзины.
     *
     * @param productId идентификатор продукта
     * @param cartDto   объект корзины покупок, полученный из сессии
     * @return объект ModelAndView с обновленной корзиной или главной страницей, если productId отсутствует
     */
    @PostMapping("/remove/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") String productId,
                                              @Valid @ModelAttribute("cartDto") CartDto cartDto) {
        ModelMap modelParams = new ModelMap();
        if (productId != null) {
            Integer id = Integer.parseInt(productId);
            modelParams.addAttribute("cartDto", cartService.removeProduct(id, cartDto));
            return new ModelAndView(PageName.CART_PAGE, modelParams);
        }
        return new ModelAndView(PageName.HOME_PAGE, modelParams);
    }

    /**
     * Очищает корзину покупок.
     *
     * @param cartDto объект корзины покупок, полученный из сессии
     * @return объект ModelAndView с обновленной корзиной
     */
    @PostMapping("/clear")
    public ModelAndView clearCart(@ModelAttribute("cartDto") CartDto cartDto) {
        ModelMap modelParams = new ModelMap();
        clearCart(cartDto, modelParams);
        return new ModelAndView(PageName.CART_PAGE, modelParams);
    }

    /**
     * Сохраняет заказ.
     *
     * @param cartDto объект корзины покупок, полученный из сессии
     * @param userId  идентификатор пользователя
     * @return объект ModelAndView с обновленной корзиной
     */
    @PostMapping("/order/{userId}")
    public ModelAndView saveOrder(@ModelAttribute("cartDto") CartDto cartDto,
                                  @PathVariable String userId) {
        ModelMap modelParams = new ModelMap();
        if (cartDto != null && userId != null) {
            Integer id = Integer.valueOf(userId);
            Order savedCart = orderService.save(cartDto, id);
            if (savedCart != null) {
                clearCart(cartDto, modelParams);
            }
        }
        return new ModelAndView(PageName.CART_PAGE, modelParams);
    }

    /**
     * Вспомогательный метод для очистки корзины покупок и добавления обновленного объекта
     * в модель.
     *
     * @param cartDto     объект корзины покупок, полученный из сессии
     * @param modelParams модель для передачи данных в представление
     */
    private void clearCart(CartDto cartDto, ModelMap modelParams) {
        modelParams.addAttribute("cartDto", cartService.clear(cartDto));
    }

}