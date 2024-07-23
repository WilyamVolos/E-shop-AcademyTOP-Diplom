package ru.academytop.eshop.repository;

import ru.academytop.eshop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Репозиторий для управления сущностями {@link Image}.
 * <p>
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * и выполнения запросов к базе данных для сущностей {@link Image}. Он наследуется от {@link JpaRepository},
 * что обеспечивает базовую функциональность для работы с сущностями JPA.
 * </p>
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    /**
     * Находит изображение по идентификатору продукта.
     * <p>
     * Этот метод выполняет поиск изображения, связанного с указанным идентификатором продукта.
     * Он возвращает {@link Optional}, чтобы обработать случай, когда изображение для данного продукта не найдено.
     * </p>
     *
     * @param productId идентификатор продукта для поиска изображения.
     * @return {@link Optional} содержащий изображение, если оно найдено, иначе пустой {@link Optional}.
     */
    Optional<Image> findImageByProductId(Integer imageId);

}
