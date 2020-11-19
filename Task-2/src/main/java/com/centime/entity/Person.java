package com.centime.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "person")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    private String name;

    private String color;

    public Person(int parentId, String name, String color) {
        this.parentId = parentId;
        this.name = name;
        this.color = color;
    }
}
