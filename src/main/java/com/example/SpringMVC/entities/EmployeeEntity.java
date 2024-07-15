package com.example.SpringMVC.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name="Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private String role;
    //using @JsonProperty because using serialization and deserialization
    //it takes is as default and then it will show only active
    @JsonProperty("isActive")
    private Boolean isActive;
    private Double salary;
    private String password;

}
