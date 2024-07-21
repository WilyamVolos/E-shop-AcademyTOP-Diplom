package ru.academytop.eshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сущность продукта в системе электронной коммерции.
 * Хранит информацию о продукте, включая его идентификатор, название,
 * описание, цену, категорию, заказы, в которые он включен, и изображение.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    /**
     * Уникальный идентификатор продукта.
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    /**
     * Название продукта.
     */
    private String name;
    /**
     * Описание продукта.
     * Максимальная длина - 400 символов.
     */
    @Column(length = 400)
    private String description;
    /**
     * Цена продукта.
     */
    private double price;
    /**
     * Категория, к которой относится продукт.
     * Связь многие к одному с сущностью Category.
     * Значение не может быть null.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    /**
     * Список заказов, в которые включен продукт.
     * Связь многие ко многим с сущностью Order.
     * Используется ленивое извлечение (FetchType.LAZY) для повышения производительности.
     * Каскадное слияние (CascadeType.MERGE) для сохранения изменений.
     * Аннотация @JsonIgnore предотвращает сериализацию списка заказов в JSON.
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            }, mappedBy = "products")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
    /**
     * Изображение продукта.
     * Связь один к одному с сущностью Image.
     * Каскадное сохранение (CascadeType.ALL) для сохранения изображения вместе с продуктом.
     * Используется PrimaryKeyJoinColumn для связывания с идентификатором продукта.
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Image image;

    /**
     * Переопределение метода equals для сравнения продуктов по идентификатору.
     * Два объекта Product считаются равными, если у них одинаковый идентификатор.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        // Сравнение с самим собой
        if (this == o) return true;
        // Проверка типов и нулевых значений
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        // Сравнение идентификаторов
        return productId != null && Objects.equals(productId, product.productId);
    }

    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для продукта на основе его класса.
     *
     * @return хэш-код продукта
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();// Генерация хэш-кода на основе класса
    }
}

