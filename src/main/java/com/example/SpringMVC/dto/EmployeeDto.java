package com.example.SpringMVC.dto;

import com.example.SpringMVC.annotations.EmployeeRoleValidation;
import com.example.SpringMVC.annotations.Password;
import com.example.SpringMVC.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareMixin;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class EmployeeDto {
    @NotBlank(message = "null name")
    @Size(min=3, max=10, message = "no of character should be in range :[3,10]")
    private String name;
    @Email
    private String email;
    private Long id;

    @Max(value = 80,message = "Age of Employee cannot be more than 80")
    @Min(value=18, message = "Age of Employee cannot be less than 18")
    @PrimeNumberValidation  (message = "Age is not a prime number")
    private Integer age;

    @NotBlank(message ="Role of employee cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$",message ="Role of Employee can be Admin or User")
    @EmployeeRoleValidation //custom annotation
    private String role; // ADMIN , USER

    @Past(message = "Date of joining cannot be in future")
    private LocalDate dateOfJoining;
    //using @JsonProperty because using serialization and deserialization
    //it takes is as default and then it will show only active@JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;

    @NotNull(message = "salary cannot be null")
    @Positive(message = "Salary cannot be negative")
    @Digits(integer =6,fraction = 2, message = "decimal cannot be more than 2")
    @DecimalMax(value="100000.99")
    @DecimalMin(value="100.50")
    private Double salary;

    @Password
    private String password;


    public EmployeeDto(){
        
    }

//    public EmployeeDto(String name, String email, Long id, Integer age, LocalDate dateOfJoining, Boolean isActive) {
//        this.name = name;
//        this.email = email;
//        this.id = id;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isActive = isActive;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//   }
}
