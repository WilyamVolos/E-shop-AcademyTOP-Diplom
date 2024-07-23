package ru.academytop.eshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * DTO (Data Transfer Object) для представления корзины покупок.
 * Содержит информацию о товарах в корзине, общей стоимости и количестве товаров.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    /**
     * Список продуктов в корзине.
     * Не может быть null, инициализируется пустым списком по умолчанию.
     */
    @NotNull
    private List<ProductDto> products = new ArrayList<>();
    /**
     * Общая стоимость товаров в корзине.
     * Значение должно быть не менее 0.0.
     */
    @DecimalMin(value = "0.0")
    private double totalPrice;
    /**
     * Общее количество товаров в корзине.
     */
    private int quantity;
}