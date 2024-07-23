package ru.academytop.eshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Аннотация @Configuration указывает, что этот класс является конфигурационным классом Spring
@Configuration
public class ConfigBean {
    // Аннотация @Bean указывает, что метод создает bean, который будет управляться Spring
    @Bean
    public ModelMapper modelMapper() {
        // Создание и возврат экземпляра ModelMapper, который будет использоваться для маппинга объектов
        return new ModelMapper();
    }
}
