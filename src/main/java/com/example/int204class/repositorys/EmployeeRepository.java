package com.example.int204class.repositorys;

import com.example.int204class.entitys.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
