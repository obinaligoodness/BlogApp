package africa.semicolon.BloggingApp.services;

import africa.semicolon.BloggingApp.dtos.requests.PostRequest;
import africa.semicolon.BloggingApp.dtos.response.PostResponse;
import africa.semicolon.BloggingApp.exceptions.NotFoundException;
import africa.semicolon.BloggingApp.models.Comment;
import africa.semicolon.BloggingApp.models.Post;
import africa.semicolon.BloggingApp.models.User;
import africa.semicolon.BloggingApp.repositories.PostRepository;
import africa.semicolon.BloggingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public PostResponse post(PostRequest postRequest) throws NotFoundException {
        Post post = new Post();
        if (postRequest.getTitle()==null && postRequest.getEmail()==null && postRequest.getContent()==null) throw new NullPointerException("All fields must be filled");
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        User foundUser = userRepository.findUserByEmail(postRequest.getEmail());
        if (foundUser==null)throw new NullPointerException("User with this email is not found ");
        post.setUser(foundUser);
        Post savedPost = postRepository.save(post);
        PostResponse postResponse = new PostResponse();
        postResponse.setAuthor(foundUser.getUserName());
        postResponse.setTitle(savedPost.getTitle());
        postResponse.setContent(savedPost.getContent());
        return postResponse;
    }
}
