package com.centime.concatservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserBean {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Surname")
    private String surname;

}
