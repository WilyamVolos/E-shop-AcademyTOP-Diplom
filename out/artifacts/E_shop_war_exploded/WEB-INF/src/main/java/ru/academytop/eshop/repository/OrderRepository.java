package ru.academytop.eshop.repository;

import ru.academytop.eshop.entity.Order;
import ru.academytop.eshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Репозиторий для управления сущностями {@link Order}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * и выполнения запросов к базе данных для сущностей {@link Order}. Он наследуется от {@link JpaRepository},
 * что обеспечивает базовую функциональность для работы с сущностями JPA.
 * </p>
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * Находит все заказы, связанные с указанным пользователем.
     * <p>
     * Этот метод выполняет поиск всех заказов, которые принадлежат определенному пользователю.
     * Он возвращает список заказов, связанных с указанным пользователем.
     * </p>
     *
     * @param user объект {@link User}, для которого необходимо найти все заказы.
     * @return список {@link Order} для указанного пользователя.
     */
    List<Order> findAllByUser(User user);
    /**
     * Находит заказ по его идентификатору.
     * <p>
     * Этот метод выполняет поиск заказа по уникальному идентификатору заказа.
     * Он возвращает заказ, если он найден, или `null`, если заказ с таким идентификатором не существует.
     * </p>
     *
     * @param id уникальный идентификатор заказа.
     * @return объект {@link Order} с указанным идентификатором, или `null`, если заказ не найден.
     */
    Order findByOrderId(Integer id);

}