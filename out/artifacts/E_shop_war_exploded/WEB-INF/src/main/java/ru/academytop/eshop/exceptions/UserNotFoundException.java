package ru.academytop.eshop.exceptions;

/**
 * Исключение, которое выбрасывается в случае, если пользователь не найден.
 * <p>
 * Это исключение используется для обработки ситуаций, когда запрашиваемый пользователь не существует в базе данных.
 * Оно наследуется от {@link RuntimeException} и может быть выброшено в любом месте кода, где требуется обработка ошибки
 * отсутствия пользователя.
 * </p>
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Конструктор для создания нового экземпляра {@code UserNotFoundException}.
     *
     * @param message Сообщение, описывающее причину исключения. Это сообщение будет отображаться в логе ошибок
     *                и может быть использовано для отладки.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

}