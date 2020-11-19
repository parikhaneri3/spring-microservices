package com.centime.service;

import com.centime.aspects.LogMethodParam;
import com.centime.aspects.LogMethodParam.*;
import com.centime.entity.Person;
import com.centime.model.PersonModel;
import com.centime.model.request.PersonRequest;
import com.centime.repository.PersonRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @LogMethodParam(logLevel = LoggerLevel.INFO)
    public Person addPerson(PersonRequest personRequest) {
        return personRepository.save(personRequest.savePersonDetails());
    }

    @LogMethodParam(logLevel = LoggerLevel.DEBUG)
    public Person getPersonDetailsById(int id) {
        Optional<Person> ch = personRepository.findById(id);
        return ch.orElse(null);
    }

    @LogMethodParam(logLevel = LoggerLevel.INFO)
    public List<PersonModel> findAll() {
        return retrieveParentChildRelations(personRepository.findAll());
    }

    private List<PersonModel> retrieveParentChildRelations(List<Person> persons) {
        Map<Integer, PersonModel> map = persons.stream()
                .collect(Collectors.toMap(Person::getId, PersonModel::getPersonDetails));

        List<PersonModel> personModels = new ArrayList<PersonModel>();
        for (Person person : persons) {
            if (map.containsKey(person.getParentId())) {
                map.get(person.getParentId()).addSubclass(map.get(person.getId()));
            } else {
                personModels.add(map.get(person.getId()));
            }
        }
        return personModels;
    }

}
