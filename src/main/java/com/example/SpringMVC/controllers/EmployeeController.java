package com.example.SpringMVC.controllers;

import com.example.SpringMVC.dto.EmployeeDto;
import com.example.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVC.repositories.EmployeeRepository;
import com.example.SpringMVC.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
    public ResponseEntity<EmployeeDto> getEmployeeByID(@PathVariable Long id){
        Optional<EmployeeDto> employeeDto= employeeService.getEmployeeByID(id);
        return employeeDto.
                map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(()-> new NoSuchElementException("Employee not found"));
        //.orElse(ResponseEntity.notFound().build());
        //return new EmployeeDto("Aman","ajoshi@gmail.com",1L,25, LocalDate.of(2022,8,22),true);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleEmployeeNotFound(){
        return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);

    }

    @GetMapping(path="/findAllEmployee")
    public ResponseEntity<List<EmployeeDto>> findAllEmployee(@RequestParam(required = false) String age){

        List<EmployeeDto>employeeDtoList=employeeService.findAllEmployee();

        return ResponseEntity.ok(employeeDtoList);

    }

    @PostMapping(path="/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        EmployeeDto employeeDto1= employeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable Long id){
        EmployeeDto employeeDto1= employeeService.updateEmployee(employeeDto,id);
        return ResponseEntity.ok(employeeDto1);
    }
    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
        Boolean gotDeleted=employeeService.deleteEmployee(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();

    }

    @PatchMapping(path="/patch/{id}")
    public ResponseEntity<EmployeeDto> patchEmployee(@RequestBody Map<String, Object> employeeUpdate,@PathVariable Long id){
        EmployeeDto employeeDto= employeeService.patchEmployee(id,employeeUpdate);
        if(employeeDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);

    }
}
