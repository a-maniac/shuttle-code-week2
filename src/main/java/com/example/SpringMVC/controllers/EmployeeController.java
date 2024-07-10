package com.example.SpringMVC.controllers;

import com.example.SpringMVC.dto.EmployeeDto;
import com.example.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVC.repositories.EmployeeRepository;
import com.example.SpringMVC.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/")
    public String getMySuperSecretMessage(){
        return "The secret msg is : ajsdhajd@232knda";
    }

    @GetMapping(path = "/{id}")
    // we can define name as name inside Path variable, or we can keep same parameter name of requested parameter and param
    // defined inside the function.
    //public EmployeeDto getEmployeeByID(@PathVariable(name="id") Long id){
    public EmployeeDto getEmployeeByID(@PathVariable Long id){
        return employeeService.getEmployeeByID(id);
        //return new EmployeeDto("Aman","ajoshi@gmail.com",1L,25, LocalDate.of(2022,8,22),true);
    }

    @GetMapping(path="/findAllEmployee")
    public List<EmployeeDto> findAllEmployee(@RequestParam(required = false) String age){

        return employeeService.findAllEmployee();

    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createNewEmployee(employeeDto);
    }

    @PutMapping(path="/update/{id}")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable Long id){
        return employeeService.updateEmployee(employeeDto,id);
    }
    @DeleteMapping(path="/delete/{id}")
    public boolean deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);

    }

    @PatchMapping(path="/patch/{id}")
    public EmployeeDto patchEmployee(@RequestBody Map<String, Object> employeeUpdate,@PathVariable Long id){
        return employeeService.patchEmployee(id,employeeUpdate);

    }
}
