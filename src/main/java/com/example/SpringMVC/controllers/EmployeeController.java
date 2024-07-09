package com.example.SpringMVC.controllers;

import com.example.SpringMVC.dto.EmployeeDto;
import com.example.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVC.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/")
    public String getMySuperSecretMessage(){
        return "The secret msg is : ajsdhajd@232knda";
    }

    @GetMapping(path = "/{id}")
    // we can define name as name inside Path variable, or we can keep same parameter name of requested parameter and param
    // defined inside the function.
    //public EmployeeDto getEmployeeByID(@PathVariable(name="id") Long id){
    public EmployeeEntity getEmployeeByID(@PathVariable Long id){
        return employeeRepository.findById(id).orElse(null);
        //return new EmployeeDto("Aman","ajoshi@gmail.com",1L,25, LocalDate.of(2022,8,22),true);
    }

    @GetMapping(path="/findAllEmployee")
    public List<EmployeeEntity> findAllEmployee(@RequestParam(required = false) String age){

        List<EmployeeDto> employeeDtoList=new ArrayList<>();
        EmployeeDto firstEmployee=new EmployeeDto();
        firstEmployee.setId(1L);
        firstEmployee.setAge(25);
        firstEmployee.setName("Aman");
        firstEmployee.setEmail("xyz@gmail.com");
        firstEmployee.setActive(true);
        firstEmployee.setDateOfJoining(LocalDate.of(2022,8,22));

        EmployeeDto secondEmployee=new EmployeeDto();
        secondEmployee.setId(2L);
        secondEmployee.setAge(25);
        secondEmployee.setName("Ashutosh");
        secondEmployee.setEmail("abc@gmail.com");
        secondEmployee.setActive(true);
        secondEmployee.setDateOfJoining(LocalDate.of(2022,8,22));

        employeeDtoList.add(firstEmployee);
        employeeDtoList.add(secondEmployee);

        return employeeRepository.findAll();

    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeDto){
        return employeeRepository.save(employeeDto);
    }
}
