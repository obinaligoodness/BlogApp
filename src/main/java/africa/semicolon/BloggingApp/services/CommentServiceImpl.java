package africa.semicolon.BloggingApp.services;

import africa.semicolon.BloggingApp.dtos.requests.CommentRequest;
import africa.semicolon.BloggingApp.dtos.response.CommentResponse;
import africa.semicolon.BloggingApp.models.Comment;
import africa.semicolon.BloggingApp.models.Post;
import africa.semicolon.BloggingApp.models.User;
import africa.semicolon.BloggingApp.repositories.CommentRepository;
import africa.semicolon.BloggingApp.repositories.PostRepository;
import africa.semicolon.BloggingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
//    private final UserService userService;

    @Override
    public CommentResponse comment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        User foundUser = userRepository.findUserByEmail(commentRequest.getUserEmail());
        List<Post> userPosts = postRepository.findPostByUser(foundUser);
        User sender = userRepository.findUserByEmail(commentRequest.getSenderEmail());
        Post foundPost = postRepository.findPostByTitle(commentRequest.getTitle());

        comment.setPost(foundPost);
        comment.setAuthor(sender.getUserName());
        comment.setContent(commentRequest.getContent());
        foundPost.getComments().add(comment);

        commentRepository.save(comment);

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setMessage("successful");
        return commentResponse;
    }
}
