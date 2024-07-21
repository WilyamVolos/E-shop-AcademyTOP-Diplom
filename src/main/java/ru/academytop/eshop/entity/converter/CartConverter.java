package ru.academytop.eshop.entity.converter;

import ru.academytop.eshop.dto.CartDto;
import ru.academytop.eshop.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования между сущностями Cart и DTO CartDto.
 * Использует ModelMapper для упрощения преобразования данных.
 */
@Component
public class CartConverter {
    private final ModelMapper modelMapper;
    /**
     * Конструктор конвертера, инициализирующий ModelMapper.
     *
     * @param modelMapper экземпляр ModelMapper для преобразования объектов
     */
    @Autowired
    public CartConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    /**
     * Преобразует объект Cart в CartDto.
     *
     * @param cart объект сущности Cart
     * @return соответствующий объект CartDto
     */
    public CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }
    /**
     * Преобразует объект CartDto в Cart.
     *
     * @param cartDto объект DTO CartDto
     * @return соответствующий объект сущности Cart
     */
    public Cart convertToEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);
    }
}
