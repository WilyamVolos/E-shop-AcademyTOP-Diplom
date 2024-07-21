package ru.academytop.eshop.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.academytop.eshop.utils.PageName;

/**
 * Глобальный обработчик исключений, который обрабатывает исключения, возникающие в контроллерах приложения.
 * Используется для перехвата и обработки исключений, а также для отображения соответствующих сообщений об ошибках.
 */
@Slf4j// Аннотация Lombok для автоматической генерации логгера
@ControllerAdvice// Аннотация Spring, которая указывает, что этот класс будет обрабатывать исключения по всему приложению
public class GlobalExceptionHandler {
    /**
     * Обработчик исключений для случая, когда пользователь не найден.
     * При возникновении исключения {@link UserNotFoundException} данный метод будет вызываться.
     *
     * @param ex исключение, которое было выброшено
     * @return объект {@link ModelAndView}, указывающий на страницу ошибки с сообщением
     */
    @ExceptionHandler(UserNotFoundException.class)// Указывает, что этот метод обрабатывает исключения типа UserNotFoundException
    public ModelAndView handleUserNotFoundException(UserNotFoundException ex) {
        // Создание объекта ModelAndView для отображения страницы ошибки
        ModelAndView modelAndView = new ModelAndView(PageName.ERROR_PAGE);
        // Добавление сообщения об ошибке в модель
        modelAndView.addObject("User not found", ex.getMessage());
        // Логирование ошибки с уровнем ERROR
        log.error("User not found", ex);
        // Возврат объекта ModelAndView, который будет использоваться для отображения страницы ошибки
        return modelAndView;
    }

}