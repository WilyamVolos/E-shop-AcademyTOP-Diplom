package ru.academytop.eshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сущность корзины покупок.
 * Представляет корзину, содержащую товары, общую стоимость и количество товаров.
 */
@Setter
@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
@NoArgsConstructor
public class Cart {
    /**
     * Идентификатор корзины (уникальный идентификатор).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;
    /**
     * Дата и время создания корзины.
     * Автоматически устанавливается при создании записи.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    /**
     * Список продуктов, содержащихся в корзине.
     * Этот список не сохраняется в базе данных, используется только для временного хранения данных.
     */
    @Transient
    private List<Product> products = new ArrayList<>();
    /**
     * Общая стоимость товаров в корзине.
     */
    private double totalPrice;
    /**
     * Общее количество товаров в корзине.
     */
    private int quantity;

    /**
     * Переопределение метода equals для сравнения корзин по идентификатору.
     * Два объекта Cart считаются равными, если у них одинаковый идентификатор.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return cartId != null && Objects.equals(cartId, cart.cartId);
    }

    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для корзины на основе ее класса.
     *
     * @return хэш-код корзины
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}