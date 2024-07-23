package ru.academytop.eshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * DTO (Data Transfer Object) для представления информации об изображении продукта.
 * Содержит идентификатор продукта и ссылку на изображение.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto implements Serializable {
    /**
     * Идентификатор продукта, к которому относится изображение.
     */
    private Integer productId;
    /**
     * Ссылка на изображение продукта.
     * Не может быть пустой или состоящей из пробелов.
     */
    @NotBlank(message = "Link is required")
    private String link;
}