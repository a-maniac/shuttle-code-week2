package com.example.SpringMVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
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
    private Integer age;
    private LocalDate dateOfJoining;
    //using @JsonProperty because using serialization and deserialization
    //it takes is as default and then it will show only active@JsonProperty("isActive")
    private Boolean isActive;

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
