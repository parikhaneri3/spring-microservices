package com.centime.model.request;

import com.centime.entity.Person;

import lombok.Data;

@Data
public class PersonRequest {

    private int parentId;

    private String name;

    private String color;

    public Person savePersonDetails() {
        return new Person(this.parentId, this.name, this.color);
    }

}
