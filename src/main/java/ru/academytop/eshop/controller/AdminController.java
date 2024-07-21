//package by.petrovich.eshop.controller;
//
//
//import by.petrovich.eshop.entity.Product;
//import by.petrovich.eshop.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
///**
// * Контроллер для управления административными функциями.
// */
//
//@Controller
//public class AdminController {
//
//    private final ProductService productService;
//
//    @Autowired
//    public AdminController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    /**
//     * Обработчик GET-запросов на страницу управления продуктами.
//     *
//     * @param model модель для передачи данных в представление
//     * @return имя представления
//     */
//
//    @GetMapping("/admin")
//    public String adminPage(Model model) {
//        model.addAttribute("products", productService.findAll());
//        return "admin";
//    }
//
//    /**
//     * Обработчик POST-запросов для добавления нового продукта.
//     *
//     * @param name имя продукта
//     * @param description описание продукта
//     * @param price цена продукта
//     * @param image изображение продукта
//     * @return перенаправление на страницу управления продуктами
//     * @throws IOException если возникает ошибка при сохранении изображения
//     */
//
//    @PostMapping("/admin/addProduct")
//    public String addProduct(@RequestParam String name,
//                             @RequestParam String description,
//                             @RequestParam double price,
//                             @RequestParam("image") MultipartFile image) throws IOException {
//        Product product = new Product();
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
////        product.setImage(image.getBytes());
////        productService.save(product);
//        return "redirect:/admin";
//    }
//}

