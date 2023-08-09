package africa.semicolon.BloggingApp.repositories;

import africa.semicolon.BloggingApp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
