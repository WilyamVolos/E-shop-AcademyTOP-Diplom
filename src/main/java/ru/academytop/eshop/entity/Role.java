package ru.academytop.eshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Сущность роли пользователя в системе.
 * Хранит информацию о роли, включая идентификатор, название и связанные с этой ролью пользователи.
 */
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    /**
     * Уникальный идентификатор роли.
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    /**
     * Название роли.
     * Значение не может быть null и должно быть уникальным.
     */
    @Column(nullable = false, unique = true)
    private String name;
    /**
     * Список пользователей, которые имеют эту роль.
     * Связь один ко многим с сущностью User.
     * Поле не обязательно загружать (FetchType.LAZY) для повышения производительности.
     */
    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();
    /**
     * Переопределение метода equals для сравнения ролей по идентификатору.
     * Две роли считаются равными, если у них одинаковый идентификатор.
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
        Role role = (Role) o;
        // Сравнение идентификаторов
        return id != null && Objects.equals(id, role.id);
    }
    /**
     * Переопределение метода hashCode для корректного использования в коллекциях.
     * Возвращает хэш-код для роли на основе ее класса.
     *
     * @return хэш-код роли
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();} // Генерация хэш-кода на основе класса
}