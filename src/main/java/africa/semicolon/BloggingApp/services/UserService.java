package africa.semicolon.BloggingApp.services;

import africa.semicolon.BloggingApp.dtos.requests.*;
import africa.semicolon.BloggingApp.dtos.response.*;
import africa.semicolon.BloggingApp.exceptions.AlreadyExists;
import africa.semicolon.BloggingApp.exceptions.NotFoundException;
import africa.semicolon.BloggingApp.exceptions.NullException;
import africa.semicolon.BloggingApp.models.Post;
import africa.semicolon.BloggingApp.models.User;

import java.util.List;

public interface UserService {
    RegistrationResponse registerUser(RegistrationRequest registrationRequest) throws NullException, AlreadyExists;
    LoginResponse loginUser(LoginRequest loginRequest) throws NotFoundException, NullException;
    DeleteResponse deleteUser(DeleteRequest deleteRequest) throws NotFoundException;
    UpdateResponse updateUser(String email, UpdateRequest updateRequest) throws NotFoundException, NullException;
    List<User> findAllUser();
    User findUserByEmail(String email) throws NotFoundException;
    PostResponse post(PostRequest postRequest) throws NotFoundException;
    DeletePostResponse deletePost(DeletePostRequest deletePostRequest);
    List<Post> getUserPost(String email);

    CommentResponse comment (CommentRequest commentRequest);



}
