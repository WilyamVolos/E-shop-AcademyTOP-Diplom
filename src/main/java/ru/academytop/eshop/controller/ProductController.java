package ru.academytop.eshop.controller;

import ru.academytop.eshop.entity.Product;
import ru.academytop.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Контроллер для обработки запросов, связанных с продуктами.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    /**
     * Конструктор контроллера, инициализирующий сервис продуктов.
     *
     * @param productService сервис для работы с продуктами
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Обрабатывает GET-запрос для отображения страницы продукта по его идентификатору.
     *
     * @param productId идентификатор продукта
     * @return объект ModelAndView с именем представления и атрибутами модели
     */
    @GetMapping("/{productId}")
    public ModelAndView showProductPage(@PathVariable("productId") Integer productId) {
        ModelMap model = new ModelMap();
        // Проверка на null для идентификатора продукта
        if (productId != null) {
            // Получение продукта по идентификатору
            Optional<Product> product = productService.findById(productId);
            // Добавление продукта в модель, если он найден
            model.addAttribute("product", product.get());
        }
        // Возвращение представления с данными продукта
        return new ModelAndView(PageName.PRODUCT_PAGE, model);
    }
    /**
     * Обрабатывает GET-запрос для поиска продуктов по ключевому слову.
     *
     * @param searchKey ключевое слово для поиска по имени и описанию продукта
     * @return объект ModelAndView с именем представления и атрибутами модели
     */
    @GetMapping("/search")
    public ModelAndView advancedSearch(@RequestParam("searchKey") String searchKey) {
        ModelMap model = new ModelMap();
        // Поиск продуктов по ключевому слову
        Set<Product> products = productService.searchProductsByNameAndDescription(searchKey);
        // Добавление найденных продуктов в модель
        model.addAttribute("products", products);
        // Возвращение представления с результатами поиска
        return new ModelAndView(PageName.PRODUCT_PAGE, model);
    }
    /**
     * Обрабатывает GET-запрос для отображения списка продуктов с пагинацией.
     *
     * @param page номер текущей страницы (по умолчанию 1)
     * @param size количество продуктов на странице (по умолчанию 3)
     * @return объект ModelAndView с именем представления и атрибутами модели
     */
    @RequestMapping(value = "/listProducts", method = RequestMethod.GET)
    public ModelAndView listProducts(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        ModelMap model = new ModelMap();
        // Определение текущей страницы и размера страницы
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        // Получение страницы продуктов с пагинацией
        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        // Добавление страницы продуктов в модель
        model.addAttribute("productPage", productPage);
        // Вычисление общего числа страниц и добавление номеров страниц в модель
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        // Возвращение представления со списком продуктов и номерами страниц
        return new ModelAndView(PageName.PRODUCT_PAGE, model);
    }
}