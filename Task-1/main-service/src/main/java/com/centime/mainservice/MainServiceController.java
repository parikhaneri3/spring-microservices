package com.centime.mainservice;

import com.netflix.client.ClientException;
import com.centime.mainservice.feignclients.ConcatFeignClient;
import com.centime.mainservice.feignclients.WelcomeFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainServiceController {

    private static final Logger log = LoggerFactory.getLogger(MainServiceController.class);

    private final WelcomeFeignClient welcomeFeignClient;

    private final ConcatFeignClient concatFeignClient;

    public MainServiceController(WelcomeFeignClient welcomeFeignClient,
            ConcatFeignClient concatFeignClient) {
        this.welcomeFeignClient = welcomeFeignClient;
        this.concatFeignClient = concatFeignClient;
    }

    @GetMapping(path = "/health", produces = { "application/text" })
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Up");
    }

    @PostMapping(value = "/welcome-user", consumes = { "application/json" }, produces = {
            "application/text" })
    public ResponseEntity<String> welcomeUser(
            @RequestBody @Valid @NotNull(message = "UserBean object is required") UserBean userBean) {
        log.info("Welcoming message.");
        String welcomeText = welcomeFeignClient.getMessage().getBody();
        log.info("Retrieved welcoming - {}", welcomeText);
        String username = concatFeignClient.concat(userBean).getBody();
        log.info("Retrieved concat name - {}", username);
        return ResponseEntity.ok(welcomeText + " " + username);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClientException.class)
    public Map<String, String> handleClientException(ClientException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Client - " + ex.getErrorMessage().split(":")[1].trim()
                + " not registered with Eureka server");
        return errors;
    }

}
