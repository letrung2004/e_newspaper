package com.newspaper.contentservice.repository.httpclient;

import com.newspaper.contentservice.configuration.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comment-service"
        , url = "${app.services.comments.url}"
        , configuration = {AuthenticationRequestInterceptor.class}
)
public interface  CommentClient {
    @DeleteMapping("/article/{articleId}")
    void deleteByArticleId(@PathVariable("articleId") String articleId);
}
