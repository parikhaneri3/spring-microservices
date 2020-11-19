package com.centime.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.centime.entity.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PersonModel {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Sub Classes")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PersonModel> subClasses = null;

    public PersonModel(String name) {
        this.name = name;
    }

    public void addSubclass(PersonModel personModel) {
        if (Objects.isNull(this.subClasses)) {
            this.subClasses = new ArrayList<>();
        }
        this.subClasses.add(personModel);
    }

    public static PersonModel getPersonDetails(Person person) {
        return new PersonModel(person.getName());
    }

}
