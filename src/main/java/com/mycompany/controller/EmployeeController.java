package com.mycompany.controller;

import com.mycompany.module.Employee;
import com.mycompany.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;        //REWORK TO CHANGE TO NEW EmployeeRepository MODULE.

    @GetMapping("employees")
    public Iterable<Employee> getEmployeeList(){
        return employeeService.findAll();
    }

    @GetMapping("employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee;
        if((employee = employeeService.findById(id)).isPresent()){
            return employee;
        }
        throw new ValidationException("Employee not found");
    }

    @PostMapping("employee")
    public Employee addEmployee(@Valid @RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping("employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
//        System.out.println(employee.getEmpID());
//        Employee bufferEmployee = employeeService.findById(employee.getEmpID()).get();
//        bufferEmployee.setEmpName(employee.getEmpName());
//        bufferEmployee.setEmpAddress(employee.getEmpAddress());
//        bufferEmployee.setEmpContact(employee.getEmpContact());
//        bufferEmployee.setEmpDepartment(employee.getEmpDepartment());
//        bufferEmployee.setEmpRole(employee.getEmpRole());
//        bufferEmployee.setEmpEmail(employee.getEmpEmail());
//        return employeeService.save(bufferEmployee);
        if(employeeService.findById(employee.getEmpID()).isPresent()) {
            return employeeService.save(employee);
        }
        else{
            throw new ValidationException("Employee doesnt exist");
        }
    }

    @DeleteMapping("employee/{id}")
    public void deleteEmployee(@PathVariable Integer id) {   //Add exception for non existing id.
        if(employeeService.findById(id).isPresent()){
            employeeService.deleteById(id);
        }
        else {
            throw new ValidationException("Employee not found");
        }
    }

}
