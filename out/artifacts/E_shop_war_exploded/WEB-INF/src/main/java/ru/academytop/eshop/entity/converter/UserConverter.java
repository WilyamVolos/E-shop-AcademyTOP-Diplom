package ru.academytop.eshop.entity.converter;

import ru.academytop.eshop.dto.RegistrationFormDto;
import ru.academytop.eshop.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования между сущностями User и DTO RegistrationFormDto.
 * Использует ModelMapper для упрощения преобразования данных.
 */
@Component
public class UserConverter {
    private final ModelMapper modelMapper;
    /**
     * Конструктор конвертера, инициализирующий ModelMapper.
     *
     * @param modelMapper экземпляр ModelMapper для преобразования объектов
     */
    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    /**
     * Преобразует объект User в RegistrationFormDto.
     *
     * @param user объект сущности User
     * @return соответствующий объект RegistrationFormDto
     */
    public RegistrationFormDto convertToRegisterFormDto(User user) {
        return modelMapper.map(user, RegistrationFormDto.class);
    }
    /**
     * Преобразует объект RegistrationFormDto в User.
     *
     * @param registrationFormDto объект DTO RegistrationFormDto
     * @return соответствующий объект сущности User
     */
    public User convertRegisterToEntity(RegistrationFormDto registrationFormDto) {
        return modelMapper.map(registrationFormDto, User.class);
    }
}
