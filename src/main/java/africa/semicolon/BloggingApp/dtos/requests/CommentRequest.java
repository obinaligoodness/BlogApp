package africa.semicolon.BloggingApp.dtos.requests;

import africa.semicolon.BloggingApp.models.Post;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequest {
    private String content;
    private String title;
    private String userEmail;
    private String senderEmail;
}
