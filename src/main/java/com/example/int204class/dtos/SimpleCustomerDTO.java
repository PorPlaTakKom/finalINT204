package com.example.int204class.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCustomerDTO {
    private String lastName;
    private String firstName;
    public String getName() {
        return firstName + " "+ lastName;
    }
}
