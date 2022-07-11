package com.mycompany.SystemTest;

import com.mycompany.module.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

//The main application Should be running parallel
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SystemTest {

    @Test
    public void testSuccess() {
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/employee";
        String url = "http://localhost:8085/employee";

        Employee employee = new Employee();
        employee.setEmpName("Akash");
        employee.setEmpEmail("Akash@gmail.com");
        employee.setEmpContact("1234567890");
        employee.setEmpRole("admin");

        ResponseEntity<Employee> entity = restTemplate.postForEntity(url, employee, Employee.class);  //Posting Employee

        Employee[] employees = restTemplate.getForObject(url + "s", Employee[].class);  //Getting Employee
        Assertions.assertThat(employees).extracting(Employee::getEmpName).containsOnly(employee.getEmpName()); //Check if correct name.
//        System.out.println(employees[0].getEmpID());

        restTemplate.delete(url + "/" + employees[0].getEmpID());   //Deleting Employee
        Assertions.assertThat(restTemplate.getForObject(url + "s", Employee[].class)).isEmpty(); //Check if list is empty
    }

    @Test
    public void testGetFail() {
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/employee";
        String url = "http://localhost:8085/employee";
//      TO GET
        try {
            restTemplate.getForEntity(url + "/1000", Employee.class);  //Trying to access an employee that doesnt exist.
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
//            Assert.assertEquals("Employee not found", ex.getMessage());
        }
//      To POST
        Employee employee = new Employee();
        employee.setEmpID(1000);
        try {
            restTemplate.put(url, employee, Employee.class);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
//            Assert.assertEquals("Employee doesnt exist", ex.getMessage());
        }
    }
}