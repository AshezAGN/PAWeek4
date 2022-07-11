package com.mycompany.services;

import com.mycompany.module.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends CrudRepository<Employee,Integer> {
}
