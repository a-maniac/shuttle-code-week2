package com.example.SpringMVC.services;

import com.example.SpringMVC.dto.EmployeeDto;
import com.example.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVC.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper=modelMapper;
    }
    //we are using model mapper dependency to return a DTO not an entity
    public EmployeeDto getEmployeeByID(Long id) {
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);

        return modelMapper.map(employeeEntity,EmployeeDto.class);

    }

    public List<EmployeeDto> findAllEmployee(){
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities.stream().
                map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class)).
                collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto){
        EmployeeEntity toBeSaveDTO= modelMapper.map(employeeDto,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(toBeSaveDTO),EmployeeDto.class);
    }
}
