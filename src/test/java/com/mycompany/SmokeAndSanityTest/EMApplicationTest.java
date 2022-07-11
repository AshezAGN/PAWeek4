package com.mycompany.SmokeAndSanityTest;

import com.mycompany.controller.EmployeeController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//Smoke and Sanity Test
@RunWith(SpringRunner.class)
@SpringBootTest
class EMApplicationTest {

	@Autowired
	EmployeeController employeeController;
	@Test
	void contextLoads() {
		Assert.assertNotNull(employeeController);
	}

}
