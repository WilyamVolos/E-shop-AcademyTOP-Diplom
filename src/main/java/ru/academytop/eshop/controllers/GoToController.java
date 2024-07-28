package ru.academytop.eshop.controllers;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.service.CategoryService;
import ru.academytop.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

/**
 * Контроллер для управления навигацией между страницами.
 */
@RestController
@RequiredArgsConstructor
@SessionAttributes({"cartDto"})
public class GoToController {
    private final CategoryService categoryService;
    private final UserService userService;
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
     * Обработчик GET-запросов для отображения главной страницы.
     *
     * @return объект ModelAndView с данными для отображения главной страницы
     */
    @GetMapping("/home")
    public ModelAndView showHomePage() {
        ModelMap model = new ModelMap();
        model.addAttribute("categories", categoryService.findAll());
        return new ModelAndView(PageName.HOME_PAGE, model);
    }
    /**
     * Обработчик GET-запросов для отображения страницы администратора.
     *
     * @return объект ModelAndView с данными для отображения страницы администратора
     */
    @GetMapping("/admin")
    public ModelAndView showAdminPage() {
        ModelMap model = new ModelMap();
        model.addAttribute("users", userService.findAll());
        return new ModelAndView(PageName.ADMIN_PAGE, model);
    }
    /**
     * Обработчик GET-запросов для отображения страницы входа.
     *
     * @return объект ModelAndView для отображения страницы входа
     */
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView(PageName.LOGIN_PAGE);
    }
    /**
     * Обработчик GET-запросов для отображения страницы регистрации.
     *
     * @return объект ModelAndView для отображения страницы регистрации
     */
    @GetMapping("/registration")
    public ModelAndView showRegistrationPage() {
        return new ModelAndView(PageName.REGISTRATION_PAGE);
    }
    /**
     * Обработчик GET-запросов для отображения страницы корзины.
     *
     * @return объект ModelAndView для отображения страницы корзины
     */
    @GetMapping("/cart")
    public ModelAndView showCartPage() {
        return new ModelAndView(PageName.CART_PAGE);
    }
    /**
     * Обработчик GET-запросов для отображения страницы профиля пользователя.
     *
     * @return объект ModelAndView для отображения страницы профиля пользователя
     */
    @GetMapping("/profile")
    public ModelAndView showProfilePage() {
        return new ModelAndView(PageName.PROFILE_PAGE);
    }

}
