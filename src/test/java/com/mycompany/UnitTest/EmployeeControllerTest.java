package com.mycompany.UnitTest;

import com.mycompany.controller.EmployeeController;
import com.mycompany.module.Employee;
import com.mycompany.services.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEmployeeListTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmpName("Akash");
        employee.setEmpEmail("Akash@gmail.com");
        employee.setEmpContact("1234567890");
        employee.setEmpRole("admin");
        Iterable<Employee> employees = Arrays.asList(employee);

        Mockito.when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("http://localhost:8080/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].empName", Matchers.is("Akash")));
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmpID(1);
        employee.setEmpName("Akash");
        employee.setEmpEmail("Akash@gmail.com");
        employee.setEmpContact("1234567890");
        employee.setEmpRole("admin");
        Optional<Employee> opEmployee = Optional.of(employee);

        Mockito.when(employeeService.findById(1)).thenReturn(opEmployee);

        mockMvc.perform(get("http://localhost:8080/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empID", Matchers.is(1)));
    }
}
