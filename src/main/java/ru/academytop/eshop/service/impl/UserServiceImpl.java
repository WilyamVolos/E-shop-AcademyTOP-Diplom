package ru.academytop.eshop.service.impl;

import ru.academytop.eshop.dto.RegistrationFormDto;
import ru.academytop.eshop.entity.Role;
import ru.academytop.eshop.entity.User;
import ru.academytop.eshop.exceptions.UserNotFoundException;
import ru.academytop.eshop.repository.RoleRepository;
import ru.academytop.eshop.repository.UserRepository;
import ru.academytop.eshop.entity.model.CustomUserDetail;
import ru.academytop.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
/**
 * Реализация сервиса для управления пользователями.
 * <p>
 * Этот класс реализует интерфейсы {@link UserService} и {@link UserDetailsService}, предоставляя методы для регистрации
 * пользователей, поиска пользователей и работы с деталями пользователя.
 * </p>
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    /**
     * Конструктор для инициализации {@link UserServiceImpl}.
     *
     * @param userRepository репозиторий для работы с пользователями.
     * @param passwordEncoder компонент для кодирования паролей.
     * @param roleRepository репозиторий для работы с ролями.
     */
    @Autowired
    private UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    /**
     * Находит и возвращает всех пользователей.
     *
     * @return список всех {@link User}.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    /**
     * Регистрирует нового пользователя на основе данных из регистрационной формы.
     * <p>
     * Если пользователь с данным email не существует и роль "ROLE_USER" найдена, создается новый пользователь,
     * пароль которого кодируется перед сохранением. В противном случае выбрасывается исключение {@link BadCredentialsException}.
     * </p>
     *
     * @param registrationFormDto объект {@link RegistrationFormDto} с данными для регистрации.
     */
    @Override
    public void register(RegistrationFormDto registrationFormDto) {
        // Находим роль "ROLE_USER"
        Role role = roleRepository.findByName("ROLE_USER");
        // Проверяем, существует ли пользователь с таким email
        if (!isExist(registrationFormDto.getEmail()) && role != null) {
            User user = User.builder()
                    .name(registrationFormDto.getName())
                    .password(passwordEncoder.encode(registrationFormDto.getPassword()))
                    .email(registrationFormDto.getEmail())
                    .birthDate(registrationFormDto.getBirthDate())
                    .balance(BigDecimal.valueOf(0.0))
                    .role(role)
                    .build();
            // Сохраняем нового пользователя
            userRepository.save(user);
        } else {
            // Выбрасываем исключение, если пользователь уже существует или роль не найдена
            throw new BadCredentialsException("Bad credentials");
        }
    }
    /**
     * Загружает данные пользователя по имени (логину) для аутентификации.
     *
     * @param name имя пользователя (логин).
     * @return {@link UserDetails} объект, содержащий данные пользователя для аутентификации.
     * @throws UserNotFoundException если пользователь с данным именем не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UserNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(()
                -> new UserNotFoundException("User not found"));
        return new CustomUserDetail(user);
    }
    /**
     * Проверяет существование пользователя по его email.
     *
     * @param email email для проверки.
     * @return {@code true}, если пользователь с данным email существует, иначе {@code false}.
     */
    private boolean isExist(String email) {
        return userRepository.existsByEmail(email);
    }

}
