package com.centime.concatservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcatServiceController {

    private static final Logger log = LoggerFactory.getLogger(ConcatServiceController.class);

    @PostMapping(value = "/concat", consumes = {"application/json"}, produces = {"application/text"})
    public ResponseEntity<String> concat(@RequestBody UserBean userBean) {
        log.info("Concatenating userbean fields - {}", userBean);
        return ResponseEntity.ok(userBean.getName() + " " + userBean.getSurname());
    }

}
