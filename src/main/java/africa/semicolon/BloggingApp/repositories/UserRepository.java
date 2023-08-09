package africa.semicolon.BloggingApp.repositories;

import africa.semicolon.BloggingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
