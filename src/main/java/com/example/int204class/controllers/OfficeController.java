package com.example.int204class.controllers;

import com.example.int204class.entitys.Employee;
import com.example.int204class.entitys.Office;
import com.example.int204class.services.EmployeeService;
import com.example.int204class.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("")
    public List<Office> getAllOffice(){
        return officeService.getAllOffice();
    }

    @GetMapping("{officeCode}/emp")
    public ResponseEntity<?> getEmpByOfficeCode(@PathVariable String officeCode){
        Office o = officeService.getOfficeById(officeCode);
        List<Employee> listOfEmp = new ArrayList<>();
        for(Employee e: o.getEmployees()){
            listOfEmp.add(e);
        }
        return ResponseEntity.ok(listOfEmp);
    }

    @PostMapping("{officeCode}/emp")
    public Employee addEmpByOfficeCode(@PathVariable String officeCode, @RequestBody Employee employee){
        Office o = officeService.getOfficeById(officeCode);
        employee.setOffice(o);
        return employeeService.createEmp(employee);
    }
}
