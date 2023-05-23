package com.example.int204class.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "offices")
public class Office {
    @Id
//    @Column(name = "officeCode")
    private String officeCode;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;

    private String state;
    private String country;
    private String postalCode;
    private String territory;

    @JsonIgnore
    @OneToMany(mappedBy = "office")
    private Set<Employee> employees = new LinkedHashSet<>();
}
