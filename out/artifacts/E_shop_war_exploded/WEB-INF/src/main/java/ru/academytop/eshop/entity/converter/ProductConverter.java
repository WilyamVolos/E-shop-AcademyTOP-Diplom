package ru.academytop.eshop.entity.converter;

import ru.academytop.eshop.dto.ProductDto;
import ru.academytop.eshop.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования между сущностями Product и DTO ProductDto.
 * Использует ModelMapper для упрощения преобразования данных.
 */
@Component
public class ProductConverter {
    private final ModelMapper modelMapper;
    /**
     * Конструктор конвертера, инициализирующий ModelMapper.
     *
     * @param modelMapper экземпляр ModelMapper для преобразования объектов
     */
    @Autowired
    public ProductConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    /**
     * Преобразует объект Product в ProductDto.
     *
     * @param product объект сущности Product
     * @return соответствующий объект ProductDto
     */
    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
    /**
     * Преобразует объект ProductDto в Product.
     *
     * @param productDto объект DTO ProductDto
     * @return соответствующий объект сущности Product
     */
    public Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
