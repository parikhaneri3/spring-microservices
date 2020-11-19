package com.centime.controller;

import com.centime.entity.Person;
import com.centime.model.PersonModel;
import com.centime.model.request.PersonRequest;
import com.centime.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/add", consumes = { "application/json" }, produces = {
            "application/json" })
    public ResponseEntity<Person> addPerson(@RequestBody PersonRequest request) {
        log.info("Adding person details : {}", request.getName());
        return ResponseEntity.ok(personService.addPerson(request));
    }

    @GetMapping(path = { "" }, produces = { "application/json" })
    public ResponseEntity<List<PersonModel>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<Person> getPersonDetailsById(
            @PathVariable @Valid @PositiveOrZero(message = "Please enter a valid person id (number)") Integer id) {
        return ResponseEntity.ok(personService.getPersonDetailsById(id));
    }

}
