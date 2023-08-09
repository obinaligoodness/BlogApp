package africa.semicolon.BloggingApp.services;

import africa.semicolon.BloggingApp.dtos.requests.CommentRequest;
import africa.semicolon.BloggingApp.dtos.response.CommentResponse;
import africa.semicolon.BloggingApp.models.Comment;

public interface CommentService {
    CommentResponse comment(CommentRequest commentRequest);
}
