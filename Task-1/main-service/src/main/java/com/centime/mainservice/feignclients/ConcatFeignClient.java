package com.centime.mainservice.feignclients;

import com.centime.mainservice.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("concat-service")
public interface ConcatFeignClient {

    @PostMapping(value = "/concat")
    ResponseEntity<String> concat(UserBean userBean);

}
