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
        comment.setContent(commentRequest.getContent());
        User sender = userRepository.findUserByEmail(commentRequest.getSenderEmail());
        comment.setAuthor(sender.getUserName());
        commentRepository.save(comment);
        for (Post post:userPosts){
            if (post.getTitle().equals(commentRequest.getTitle())){
                post.getComments().add(comment);
                postRepository.save(post);
            }
        }
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setMessage("successful");
        return commentResponse;
    }
}
