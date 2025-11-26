package ma.emsi.smartrhv1.controller;

import jakarta.validation.Valid;
import ma.emsi.smartrhv1.model.AuthenticationResult;
import ma.emsi.smartrhv1.model.LoginRequest;
import ma.emsi.smartrhv1.model.User;
import ma.emsi.smartrhv1.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        AuthenticationResult result = authService.authenticateUser(
                loginRequest.getUsername(), loginRequest.getPassword());
        if (result.isAuthenticated()) {
            return ResponseEntity.ok().body(new TokenResponse(result.getToken()));
        }
        return ResponseEntity.status(401).body(java.util.Map.of("error", "Invalid credentials"));
    }

    record TokenResponse(String token) {}
}
