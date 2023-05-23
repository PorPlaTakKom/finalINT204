package com.example.int204class.services;

import com.example.int204class.entitys.Customer;
import com.example.int204class.exceptions.ItemNotFoundException;
import com.example.int204class.repositorys.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

//    public Customer getCustomerById(Integer customerId) {
//        return customerRepository.findById(customerId).orElseThrow(()->new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "Customer id "+ customerId + "Does Not Exist !!!"));
//    }

    //use exception
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(()-> new ItemNotFoundException("Customer id " + customerId + " Does Not Exist !!!"));
    }
}
