package ru.academytop.eshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * DTO (Data Transfer Object) для представления информации о продукте.
 * Содержит данные о продукте, такие как идентификатор, название, описание и цену.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    /**
     * Идентификатор продукта.
     * Должен быть положительным числом.
     */
    @Positive
    private Integer productId;
    /**
     * Название продукта.
     * Должно содержать от 2 до 20 символов.
     */
    @Size(min = 2, max = 20)
    private String name;
    /**
     * Описание продукта.
     * Должно содержать от 5 до 400 символов.
     */
    @Size(min = 5, max = 400)
    private String description;
    /**
     * Цена продукта.
     * Должна быть не менее 0.0.
     */
    @DecimalMin(value = "0.0")
    private double price;
}
