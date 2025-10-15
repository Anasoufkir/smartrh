package ma.emsi.smartrhv1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter @Setter @NoArgsConstructor // Lombok annotations pour générer getters, setters et un constructeur sans arguments
public class User {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private String role; // Champ de rôle sous forme de chaîne de caractères

    // Constructeur avec arguments utilisant Lombok
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
