package com.mycompany.IntegrationTest;

import com.mycompany.controller.EmployeeController;
import com.mycompany.module.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ValidationException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    EmployeeController employeeController;

    @Test
    public void testSuccess(){
        Employee employee = new Employee();
        employee.setEmpName("Akash");
        employee.setEmpEmail("Akash@gmail.com");
        employee.setEmpContact("1234567890");
        employee.setEmpRole("admin");

        Employee result = employeeController.addEmployee(employee);
        Iterable<Employee> employees = employeeController.getEmployeeList();
        Assertions.assertThat(employees).extracting(Employee::getEmpName).containsOnly(employee.getEmpName());
        employeeController.deleteEmployee(result.getEmpID());
        Assertions.assertThat(employeeController.getEmployeeList()).isEmpty();
    }

    @Test(expected = ValidationException.class)
    public void testFailure(){
        employeeController.deleteEmployee(1000);
    }
}
