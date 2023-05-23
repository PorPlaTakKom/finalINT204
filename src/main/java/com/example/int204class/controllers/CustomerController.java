package com.example.int204class.controllers;

import com.example.int204class.dtos.SimpleCustomerDTO;
import com.example.int204class.entitys.Customer;
import com.example.int204class.services.CustomerService;
import com.example.int204class.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ctm")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;
    @GetMapping("")
    public List<SimpleCustomerDTO> getAllCustomer(){
        List<Customer> customerList = customerService.getAll();
        return listMapper.mapList(customerList, SimpleCustomerDTO.class, modelMapper);
    }

    @GetMapping("/{customerId}")
    public SimpleCustomerDTO getSimpleCustomerById(@PathVariable Integer customerId) {
        return modelMapper.map(customerService.getCustomerById(customerId), SimpleCustomerDTO.class);
    }
}
