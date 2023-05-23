package com.example.int204class.repositorys;

import com.example.int204class.entitys.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
