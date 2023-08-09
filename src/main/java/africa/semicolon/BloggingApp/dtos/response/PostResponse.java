package africa.semicolon.BloggingApp.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostResponse {
    private String author;
    private String title;
    private String content;
    private String email;
}
