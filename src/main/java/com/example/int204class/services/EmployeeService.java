package com.example.int204class.services;

import com.example.int204class.entitys.Employee;
import com.example.int204class.repositorys.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getEmpById(Integer id){
        return employeeRepository.findById(id).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee Id " + id + " DOES NOT EXIST !!!")
        );
    }

    public Employee createEmp(Employee emp){
        return employeeRepository.save(emp);
    }

    public Employee updateEmp(Integer empId, Employee emp){
        Employee e = getEmpById(empId);
        e.setFirstName(emp.getFirstName());
        e.setLastName(emp.getLastName());
        return employeeRepository.save(e);
    }

    public void deleteEmp(Integer empId){
        employeeRepository.deleteById(empId);
    }
}
