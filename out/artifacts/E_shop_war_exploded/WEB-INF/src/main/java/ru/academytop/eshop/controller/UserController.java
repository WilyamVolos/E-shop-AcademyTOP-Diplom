package ru.academytop.eshop.controllers;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.dto.RegistrationFormDto;
import ru.academytop.eshop.entity.User;
import ru.academytop.eshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

/**
 * Контроллер для обработки запросов, связанных с пользователями.
 */
@Controller
@SessionAttributes({"user", "cartDto"})
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    /**
     * Конструктор контроллера, инициализирующий сервис пользователей.
     *
     * @param userService сервис для работы с пользователями
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Инициализирует объект пользователя для хранения в сессии.
     *
     * @return новый объект User
     */
    @ModelAttribute("user")
    public User initializeUserSessionObject() {
        return new User();
    }
    /**
     * Инициализирует объект корзины для хранения в сессии.
     *
     * @return новый объект CartDto
     */
    @ModelAttribute("cartDto")
    public CartDto initializeCartSessionObject() {
        return new CartDto();
    }
    /**
     * Обрабатывает POST-запрос для регистрации нового пользователя.
     *
     * @param registrationFormDto форма регистрации с данными пользователя
     * @param errors объект для хранения ошибок валидации
     * @param model объект ModelAndView для добавления атрибутов и выбора представления
     * @return объект ModelAndView с именем представления и данными формы регистрации
     */
    @PostMapping("/registrate")
    public ModelAndView registrate(@ModelAttribute("registration_form_dto") @Valid RegistrationFormDto registrationFormDto,
                                   BindingResult errors,
                                   ModelAndView model) {
        // Добавление объекта формы регистрации в модель
        model.addObject("registration_form_dto", registrationFormDto);
        // Регистрация пользователя через сервис
        userService.register(registrationFormDto);
        // Перенаправление на страницу входа
        return new ModelAndView(PageName.LOGIN_PAGE);
    }
}