package africa.semicolon.BloggingApp.dtos.requests;

import africa.semicolon.BloggingApp.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String author;
    private String title;
    private String content;
    private String email;
}
