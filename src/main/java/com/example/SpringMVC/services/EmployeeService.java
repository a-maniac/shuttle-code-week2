package com.example.SpringMVC.services;

import com.example.SpringMVC.dto.EmployeeDto;
import com.example.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVC.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {

        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEmployee =employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }

    public boolean deleteEmployee(Long id) {
        boolean exist =employeeRepository.existsById(id);
        if(!exist) return false;
        employeeRepository.deleteById(id);
        return true;
    }


    public EmployeeDto patchEmployee(Long id, Map<String, Object> employeeUpdate) {
        boolean exist =employeeRepository.existsById(id);
        if(!exist) return null;
        EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
        employeeUpdate.forEach(
                (field,value)->{
                    //find the field which needs to be updated
                    Field fieldObj=ReflectionUtils.findField(EmployeeEntity.class,field);
                    //since in entity all are private so to make it publicly accessible set true
                    fieldObj.setAccessible(true);
                    //setField to change a pareticular value
                    ReflectionUtils.setField(fieldObj,employeeEntity,value);
                }
        );
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }

}
