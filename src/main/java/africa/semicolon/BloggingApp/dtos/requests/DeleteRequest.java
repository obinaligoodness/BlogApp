package africa.semicolon.BloggingApp.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteRequest {
    private String email;
    private String password;
}
