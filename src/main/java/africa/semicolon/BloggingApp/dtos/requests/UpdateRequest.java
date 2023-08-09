package africa.semicolon.BloggingApp.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRequest {
    private String userName;
    private String password;
    private String email;
}
