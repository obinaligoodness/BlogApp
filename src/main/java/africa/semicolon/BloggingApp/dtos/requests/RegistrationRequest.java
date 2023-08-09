package africa.semicolon.BloggingApp.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String userName;
    private String email;
    private String password;
}
