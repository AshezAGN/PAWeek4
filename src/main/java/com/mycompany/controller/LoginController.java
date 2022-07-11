package com.mycompany.controller;

import com.mycompany.module.Employee;
import com.mycompany.module.Login;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import javax.websocket.server.PathParam;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("logins")
    public Iterable<Login> getLoginList(){
        return loginService.findAll();
    }
    @GetMapping("login")
    public boolean checkLogin(@RequestBody Login login){
        Login bufferLogin = loginService.findByLoginUsername(login.getLoginUsername()).get();
        if(bufferLogin.getLoginPassword().equals(login.getLoginPassword())) {
            return true;
        }
        return false;
    }
    @PostMapping("login/{id}")
    public Login addNewLoginDetails(@RequestBody Login login, @PathVariable int id){
        Employee employee = employeeService.findById(id).get();
//        Employee employee = employeeService.findById(1).get();
        if(employee != null){
            login.setEmployee(employee);
            loginService.save(login);       //Add Login username exist error.
            return login;
        }
        else{
            throw new ValidationException("Employee doesnt exist for login");
        }

    }
    @PutMapping("login")
    public Login changePassword(@RequestBody Login login) {
        Login bufferLogin = loginService.findByLoginUsername(login.getLoginUsername()).get();
        if (bufferLogin != null) {
            bufferLogin.setLoginPassword(login.getLoginPassword());
            loginService.save(bufferLogin);
            return bufferLogin;
        }
        else{
            throw new ValidationException("Login username doesnt exist");
        }
    }

    @DeleteMapping("login/{id}")
    public void deleteLogin(@PathVariable int id){
        if(loginService.findById(id).isPresent()) {
            loginService.deleteById(id);
        }
        else{
            throw new ValidationException("Login details doesnt exist");
        }
    }
}
