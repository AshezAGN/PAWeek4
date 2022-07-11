package com.mycompany;

import com.mycompany.module.Employee;
import com.mycompany.module.Login;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

}
