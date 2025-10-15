package ma.emsi.smartrhv1.controller;
import ma.emsi.smartrhv1.model.LoginRequest;
import ma.emsi.smartrhv1.model.AuthenticationResult;

import ma.emsi.smartrhv1.model.User;
import ma.emsi.smartrhv1.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")

public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint pour l'inscription
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (authService.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (authService.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Endpoint pour la connexion
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        AuthenticationResult result = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (result.isAuthenticated()) {
            return ResponseEntity.ok().body("Bearer " + result.getToken());
        } else {
            return ResponseEntity
                    .unprocessableEntity()
                    .body("Error: Authentication failed!");
        }
    }
}
