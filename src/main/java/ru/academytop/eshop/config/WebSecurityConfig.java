package ru.academytop.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// Аннотация @Configuration указывает, что этот класс является конфигурационным классом Spring
// Аннотация @EnableWebSecurity активирует поддержку веб-безопасности в приложении
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // Определение бина SecurityFilterChain для конфигурации безопасности HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключение защиты CSRF
                .csrf().disable()
                // Конфигурация авторизации запросов
                .authorizeHttpRequests(requests -> requests
                         // Разрешение доступа ко статическим ресурсам без аутентификации
                        .requestMatchers("/javaScript/**", "/img/**", "/CSS/**").permitAll()
                         // Разрешение доступа к определенным страницам без аутентификации
                        .requestMatchers("/", "/home", "/registration", "/users/registrate", "/login").permitAll()
                         // Ограничение доступа к странице /admin только для пользователей с ролью ADMIN
                        .requestMatchers("/admin").hasRole("ADMIN")
                         // Требование аутентификации для всех остальных запросов
                        .anyRequest().authenticated()
                )
                // Конфигурация формы входа
                .formLogin(form -> form
                        // Определение страницы логина
                        .loginPage("/login")
                        // URL для обработки логина
                        .loginProcessingUrl("/login")
                        // Имя параметра для имени пользователя
                        .usernameParameter("username")
                        // Имя параметра для пароля
                        .passwordParameter("password")
                        // URL для перенаправления после успешного логина
                        .defaultSuccessUrl("/home", true)
                        // Разрешение всем доступ к странице логина
                        .permitAll()
                )
                // Конфигурация выхода из системы
                .logout(logout -> logout
                        // URL для обработки логаута
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        // Инвалидация HTTP-сессии при выходе
                        .invalidateHttpSession(true)
                        // Очистка аутентификационной информации при выходе
                        .clearAuthentication(true)
                        // Разрешение всем доступ к странице логаута
                        .permitAll()
                )
                // Конфигурация Remember Me
                .rememberMe(Customizer.withDefaults());
        // Возврат построенного объекта SecurityFilterChain
        return http.build();
    }
    // Определение бина PasswordEncoder для шифрования паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
