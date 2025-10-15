package ma.emsi.smartrhv1.repository;


import ma.emsi.smartrhv1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username); // Pour rechercher un utilisateur par son nom d'utilisateur
    Optional<User> findByEmail(String email); // Pour rechercher un utilisateur par son email
    Boolean existsByUsername(String username); // Pour vérifier si un nom d'utilisateur existe déjà
    Boolean existsByEmail(String email); // Pour vérifier si un email existe déjà
}
