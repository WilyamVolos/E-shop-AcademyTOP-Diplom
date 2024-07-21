package ru.academytop.eshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;
/**
 * Сущность изображения товара.
 * Хранит информацию об изображении, связанное с конкретным товаром.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {
    /**
     * Уникальный идентификатор изображения.
     * Также является идентификатором связанного товара.
     */
    @Id
    @Column(name = "image_id")
    private Integer productId;
    /**
     * Ссылка на изображение.
     * Должна быть уникальной и не может быть пустой.
     */
    @NotBlank(message = "Link is required")
    @Column(unique = true)
    private String link;
    /**
     * Связь один к одному с товаром.
     * Используется для указания товара, к которому относится это изображение.
     * Каскадное сохранение и удаление включены.
     */
    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "image_id")
    private Product product;
    /**
     * Переопределение метода equals для сравнения изображений по идентификатору.
     * Два объекта Image считаются равными, если у них одинаковый идентификатор.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;
        return productId != null && Objects.equals(productId, image.productId);
    }
    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для изображения на основе его класса.
     *
     * @return хэш-код изображения
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
