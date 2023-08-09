package africa.semicolon.BloggingApp.services;

import africa.semicolon.BloggingApp.dtos.requests.*;
import africa.semicolon.BloggingApp.dtos.response.*;
import africa.semicolon.BloggingApp.exceptions.AlreadyExists;
import africa.semicolon.BloggingApp.exceptions.NotFoundException;
import africa.semicolon.BloggingApp.exceptions.NullException;
import africa.semicolon.BloggingApp.models.Post;
import africa.semicolon.BloggingApp.models.User;
import africa.semicolon.BloggingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PostService postService;
    private final CommentService commentService;
    @Override
    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) throws NullException, AlreadyExists {
        User user = new User();
        if (registrationRequest.getEmail()==null||registrationRequest.getUserName()==null||registrationRequest.getPassword()==null) throw new NullException("you have to fill in all fields");
        User foundUser = userRepository.findUserByEmail(registrationRequest.getEmail());
        if (foundUser!=null) throw new AlreadyExists("User with this email already exists");
        user.setUserName(registrationRequest.getUserName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        userRepository.save(user);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setMessage("Registration Successful");
        return registrationResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) throws NotFoundException, NullException {
        User foundUser = userRepository.findUserByEmail(loginRequest.getEmail());
        if (loginRequest.getEmail()==null||loginRequest.getPassword()==null) throw new NullException("you have to fill in all fields");
        if (foundUser==null) throw new NotFoundException("User with this email does not exist ");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserName(foundUser.getUserName());
        return loginResponse;
    }

    @Override
    public DeleteResponse deleteUser(DeleteRequest deleteRequest) throws NotFoundException {
        User foundUser = userRepository.findUserByEmail(deleteRequest.getEmail());
        if (foundUser==null) throw new NotFoundException("User with this email does not exists");
        if (!foundUser.getPassword().equals(deleteRequest.getPassword())) throw new NotFoundException("the password passed is incorrect");
        userRepository.delete(foundUser);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("delete successful");
        return deleteResponse;
    }

    @Override
    public UpdateResponse updateUser(String email, UpdateRequest updateRequest) throws NotFoundException, NullException {
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser==null) throw new NotFoundException("User with this email does not exists");
        if (updateRequest.getUserName()!=null && updateRequest.getUserName()!=""){foundUser.setUserName(updateRequest.getUserName());}
        else {foundUser.setUserName(foundUser.getUserName());}
        if (updateRequest.getEmail()!=null && updateRequest.getEmail()!=""){foundUser.setEmail(updateRequest.getEmail());}
        else {foundUser.setEmail(foundUser.getEmail());}
        userRepository.save(foundUser);
        if (updateRequest.getPassword()!=null && updateRequest.getPassword()!=""){foundUser.setPassword(updateRequest.getPassword());}
        else {foundUser.setPassword(foundUser.getPassword());}
        userRepository.save(foundUser);

        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setEmail(foundUser.getEmail());
        updateResponse.setUserName(foundUser.getUserName());
        return updateResponse;
    }

    @Override
    public List<User> findAllUser() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

    @Override
    public User findUserByEmail(String email) throws NotFoundException {
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser==null) throw new NotFoundException("User does not exist");
        return foundUser;
    }

    @Override
    public PostResponse post(PostRequest postRequest) throws NotFoundException {
        return postService.post(postRequest);
    }

    @Override
    public DeletePostResponse deletePost(DeletePostRequest deletePostRequest) {
        return null;
    }

    @Override
    public List<Post> getUserPost(String email) {
        User foundUser = userRepository.findUserByEmail(email);
        List<Post> posts  = foundUser.getPosts();
        return posts;
    }

    @Override
    public CommentResponse comment(CommentRequest commentRequest) {
        return commentService.comment(commentRequest);
    }

}
