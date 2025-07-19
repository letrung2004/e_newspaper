package com.newspaper.identityservice.repository.httpclient;

import com.newspaper.identityservice.configuration.AuthenticationRequestInterceptor;
import com.newspaper.identityservice.dto.request.ExchangeTokenRequest;
import com.newspaper.identityservice.dto.response.ExchangeTokenResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "outbound-identity"
        , url = "https://oauth2.googleapis.com"
        , configuration = {AuthenticationRequestInterceptor.class})
public interface OutboundIdentityClient {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenResponse exchangeToken(@QueryMap ExchangeTokenRequest request);
}
