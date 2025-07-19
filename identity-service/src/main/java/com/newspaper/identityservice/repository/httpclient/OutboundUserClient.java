package com.newspaper.identityservice.repository.httpclient;

import com.newspaper.identityservice.configuration.AuthenticationRequestInterceptor;
import com.newspaper.identityservice.dto.response.OutboundUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "outbound-user-client"
        , url = "https://www.googleapis.com"
        , configuration = {AuthenticationRequestInterceptor.class})
public interface OutboundUserClient {
    @GetMapping(value = "/oauth2/v1/userinfo")
    OutboundUserResponse getUserInfo(@RequestParam("alt") String alt,
                                     @RequestParam("access_token") String accessToken);
}
