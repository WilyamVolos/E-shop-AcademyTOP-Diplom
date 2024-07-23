package ru.academytop.eshop.controller;

import ru.academytop.eshop.entity.Product;
import ru.academytop.eshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Контроллер для управления категориями.
 */
@Validated
@RestController
public class CategoryController {
    private final int NUMBER_ELEMENTS_ON_PAGE = 3;
    private final CategoryService categoryService;

    /**
     * Конструктор, использующий Dependency Injection для внедрения зависимости CategoryService.
     *
     * @param categoryService сервис для работы с категориями
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Обработчик GET-запросов для отображения страницы категории.
     *
     * @param categoryId идентификатор категории (необязательный параметр)
     * @param page       номер страницы (необязательный параметр)
     * @param size       размер страницы (необязательный параметр)
     * @return объект ModelAndView с данными для отображения страницы категории
     */
    @GetMapping("/category")
    public ModelAndView showCategoryPage(
            @RequestParam Optional<Integer> categoryId,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(NUMBER_ELEMENTS_ON_PAGE);
        int id = categoryId.orElse(categoryId.orElseThrow(() -> new BadCredentialsException("Bad credentials.")));
        // Получение страницы продуктов по идентификатору категории
        Page<Product> products = categoryService.findProductsByCategoryId(id,
                PageRequest.of(currentPage - 1, pageSize));
        // Создание модели для передачи данных в представление
        ModelMap model = new ModelMap();
        model.addAttribute("products", products);
        // Если есть несколько страниц, добавляем номера страниц в модель
        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("categoryId", categoryId);
        }
        // Возвращаем ModelAndView с моделью и именем представления
        return new ModelAndView(PageName.CATEGORY_PAGE, model);
    }

}