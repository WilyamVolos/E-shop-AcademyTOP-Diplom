package ru.academytop.eshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Сущность категории товаров.
 * Представляет категорию, к которой могут принадлежать несколько товаров.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    /**
     * Уникальный идентификатор категории.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    /**
     * Название категории.
     * Не может быть пустым и должно быть уникальным в таблице.
     */
    @NotBlank(message = "Name is required")
    @Column(unique = true)
    private String name;
    /**
     * Рейтинг категории.
     * Значение должно быть в диапазоне от 1 до 5.
     */
    @Range(min = 1, max = 5)
    private Integer rating;
    /**
     * Множество товаров, принадлежащих данной категории.
     * Загружается немедленно (FetchType.EAGER) и каскадно сохраняется и удаляется вместе с категорией.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
    /**
     * Переопределение метода equals для сравнения категорий по идентификатору.
     * Две категории считаются равными, если у них одинаковый идентификатор.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return categoryId != null && Objects.equals(categoryId, category.categoryId);
    }
    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для категории на основе ее класса.
     *
     * @return хэш-код категории
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
