package com.centime.mainservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserBean {

    @JsonProperty("Name")
    @NotNull(message = "Name field is required")
    @NotBlank(message = "Name field is required")
    private String name;

    @JsonProperty("Surname")
    @NotNull(message = "Surname field is required")
    @NotBlank(message = "Surname field is required")
    private String surname;

}
