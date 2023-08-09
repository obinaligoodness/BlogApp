package africa.semicolon.BloggingApp.repositories;

import africa.semicolon.BloggingApp.models.Post;
import africa.semicolon.BloggingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostByUser(User user);
    Post findPostByTitle(String title);
}
