package com.mycompany.repository;

import com.mycompany.module.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeCrud extends CrudRepository<Employee, Integer> {
}
