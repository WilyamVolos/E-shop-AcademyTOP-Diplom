package ru.academytop.eshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сущность заказа в системе электронной коммерции.
 * Хранит информацию о заказе, включая его цену, дату создания,
 * пользователя, который сделал заказ, и товары, включенные в заказ.
 */
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    /**
     * Уникальный идентификатор заказа.
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    /**
     * Общая стоимость заказа.
     * Хранится в формате с двумя знаками после запятой.
     */
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal price;
    /**
     * Дата и время создания заказа.
     * Автоматически устанавливается при создании заказа.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    /**
     * Пользователь, который сделал заказ.
     * Связь многие к одному с сущностью User.
     * Загружается немедленно (FetchType.EAGER).
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * Список товаров, включенных в заказ.
     * Связь многие ко многим с сущностью Product.
     * Ленивая загрузка (FetchType.LAZY), чтобы не загружать все продукты сразу.
     * Каскадное слияние (CascadeType.MERGE) для сохранения изменений.
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "orders_products",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products = new ArrayList<>();

    /**
     * Переопределение метода equals для сравнения заказов по идентификатору.
     * Два объекта Order считаются равными, если у них одинаковый идентификатор.
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
        Order order = (Order) o;
        // Сравнение идентификаторов
        return orderId != null && Objects.equals(orderId, order.orderId);
    }

    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для заказа на основе его класса.
     *
     * @return хэш-код заказа
     */
    @Override
    public int hashCode() {
        // Генерация хэш-кода на основе класса
        return getClass().hashCode();
    }
}
