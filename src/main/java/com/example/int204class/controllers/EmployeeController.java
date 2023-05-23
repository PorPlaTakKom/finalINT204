package com.example.int204class.controllers;

import com.example.int204class.entitys.Employee;
import com.example.int204class.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getAllEmp(){
        return employeeService.getAll();
    }

    @GetMapping("/{empId}")
    public Employee getEmpById(@PathVariable Integer empId){
        return employeeService.getEmpById(empId);
    }

    @PutMapping("/{empId}")
    public Employee updateEmp(@PathVariable Integer empId, @RequestBody Employee emp){
        return employeeService.updateEmp(empId, emp);
    }

    @DeleteMapping("/{empId}")
    public void updateEmp(@PathVariable Integer empId){
        employeeService.deleteEmp(empId);
    }
}
