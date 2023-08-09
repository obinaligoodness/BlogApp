package africa.semicolon.BloggingApp.services;

import africa.semicolon.BloggingApp.dtos.requests.PostRequest;
import africa.semicolon.BloggingApp.dtos.response.PostResponse;
import africa.semicolon.BloggingApp.exceptions.NotFoundException;

public interface PostService {
    PostResponse post(PostRequest postRequest) throws NotFoundException;
}
