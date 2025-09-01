package com.newspaper.contentservice.repository.httpclient;

import com.newspaper.contentservice.configuration.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "search-service"
        , url = "${app.services.search.url}"
        , configuration = {AuthenticationRequestInterceptor.class}
)
public interface SearchClient {
    @DeleteMapping("/articles/{id}")
    void deleteByArticleId(@PathVariable("id") String id);
}
