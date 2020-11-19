package com.centime.mainservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("welcome-service")
public interface WelcomeFeignClient {

    @GetMapping(value = "/welcome")
    ResponseEntity<String> getMessage();

}
