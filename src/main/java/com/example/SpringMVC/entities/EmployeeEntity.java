package com.example.SpringMVC.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private String email;
    private Long id;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
