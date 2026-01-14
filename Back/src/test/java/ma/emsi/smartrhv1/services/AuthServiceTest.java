package ma.emsi.smartrhv1.services;

import ma.emsi.smartrhv1.model.AuthenticationResult;
import ma.emsi.smartrhv1.model.User;
import ma.emsi.smartrhv1.repository.UserRepository;
import ma.emsi.smartrhv1.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    void registerUser_success() {
        User user = new User("john", "john@example.com", "secret", null);
        when(userRepository.existsByUsername("john")).thenReturn(false);
        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        when(passwordEncoder.encode("secret")).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User saved = authService.registerUser(user);

        assertThat(saved.getPassword()).isEqualTo("hashed");
        assertThat(saved.getRole()).isEqualTo("USER");
        verify(userRepository).save(user);
    }

    @Test
    void registerUser_duplicateUsername_throws() {
        User user = new User("john", "john@example.com", "secret", null);
        when(userRepository.existsByUsername("john")).thenReturn(true);

        assertThatThrownBy(() -> authService.registerUser(user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Username is already taken");
    }

    @Test
    void registerUser_duplicateEmail_throws() {
        User user = new User("john", "john@example.com", "secret", null);
        when(userRepository.existsByUsername("john")).thenReturn(false);
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.registerUser(user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email is already in use");
    }

    @Test
    void authenticateUser_validCredentials_returnsToken() {
        User user = new User("john", "john@example.com", "hashed", "USER");
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("secret", "hashed")).thenReturn(true);
        when(jwtUtil.generateToken("john", "USER")).thenReturn("jwt-token");

        AuthenticationResult result = authService.authenticateUser("john", "secret");

        assertThat(result.isAuthenticated()).isTrue();
        assertThat(result.getToken()).isEqualTo("jwt-token");
    }

    @Test
    void authenticateUser_invalidPassword_returnsFalse() {
        User user = new User("john", "john@example.com", "hashed", "USER");
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);

        AuthenticationResult result = authService.authenticateUser("john", "wrong");

        assertThat(result.isAuthenticated()).isFalse();
        assertThat(result.getToken()).isNull();
    }

    @Test
    void authenticateUser_unknownUser_returnsFalse() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        AuthenticationResult result = authService.authenticateUser("ghost", "pass");

        assertThat(result.isAuthenticated()).isFalse();
    }

    @Test
    void existsByUsername_delegates() {
        when(userRepository.existsByUsername("john")).thenReturn(true);
        assertThat(authService.existsByUsername("john")).isTrue();
    }

    @Test
    void existsByEmail_delegates() {
        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        assertThat(authService.existsByEmail("john@example.com")).isFalse();
    }
}
