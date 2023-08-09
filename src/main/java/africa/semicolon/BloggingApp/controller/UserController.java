package africa.semicolon.BloggingApp.controller;

import africa.semicolon.BloggingApp.dtos.requests.*;
import africa.semicolon.BloggingApp.dtos.response.*;
import africa.semicolon.BloggingApp.exceptions.AlreadyExists;
import africa.semicolon.BloggingApp.exceptions.NotFoundException;
import africa.semicolon.BloggingApp.exceptions.NullException;
import africa.semicolon.BloggingApp.models.User;
import africa.semicolon.BloggingApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public RegistrationResponse registerUser(@RequestBody RegistrationRequest registrationRequest) throws NullException, AlreadyExists {
        RegistrationResponse registeredUser = userService.registerUser(registrationRequest);
        return registeredUser;
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws NotFoundException, NullException {
        return userService.loginUser(loginRequest);
    }

    @DeleteMapping("/deleteUser")
    public DeleteResponse deleteUser (@RequestBody DeleteRequest deleteRequest) throws NotFoundException {
        return userService.deleteUser(deleteRequest);
    }
    @PutMapping("/{email}")
    public UpdateResponse updateUser(@PathVariable String email, @RequestBody UpdateRequest updateRequest) throws NotFoundException, NullException {
        return userService.updateUser(email,updateRequest);
    }
    @GetMapping("/findAll")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }
    @PostMapping("/post")
    public PostResponse post(@RequestBody PostRequest postRequest) throws NotFoundException {
        return userService.post(postRequest);
    }
    @PostMapping("/comment")
    public CommentResponse comment(@RequestBody CommentRequest commentRequest){
        return userService.comment(commentRequest);
    }
}
