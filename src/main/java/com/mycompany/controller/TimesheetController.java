package com.mycompany.controller;

import com.mycompany.module.Employee;
import com.mycompany.module.Timesheet;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.sql.Time;
import java.time.LocalDate;

@RestController
public class TimesheetController {

    @Autowired
    TimesheetService timesheetService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("timesheets")
    public Iterable<Timesheet> getTimesheetList(){
        return timesheetService.findAll();
    }

    @GetMapping("timesheet/{id}")
    public Iterable<Timesheet> getTimesheetListById(@PathVariable int id){
        Employee employee = employeeService.findById(id).get();
        if(employee != null){
            Timesheet timesheet = new Timesheet();
            timesheet.setEmployee(employee);
            timesheet.setTimeDate(LocalDate.now());
            timesheetService.save(timesheet);
            return timesheetService.findByEmployee(employee);
        }
        else{
            throw new ValidationException("Employee id not found.");
        }
    }

    @PostMapping("timesheet/{id}")
    public Timesheet insertNewTimeshee(@PathVariable int id, @RequestBody Timesheet timesheet){
        Employee employee = employeeService.findById(id).get();
        if(employee != null){
            timesheet.setEmployee(employee);
            return timesheetService.save(timesheet);
        }
        else{
            throw new ValidationException("Employee id not found.");
        }
    }

    @DeleteMapping("timesheet/{id}")
    public void deleteTimesheet(@PathVariable int id, @RequestBody Timesheet timesheet){
        Employee employee = employeeService.findById(id).get();
        if(employee != null){
            Timesheet timesheet1 = timesheetService.findByEmployeeAndTimeDate(employee,timesheet.getTimeDate()).get();
            timesheetService.deleteById(timesheet1.getTimesheetId());
        }
        else{
            throw new ValidationException("Employee id not found.");
        }
    }
}
