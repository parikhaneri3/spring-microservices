package com.centime.welcomeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeServiceController {

    private static final Logger log = LoggerFactory.getLogger(WelcomeServiceController.class);

    @GetMapping(path = "/welcome", produces = { "application/text" })
    public ResponseEntity<String> getMessage() {
        log.info("A welcome request.");
        return ResponseEntity.ok("Hello");
    }

}
