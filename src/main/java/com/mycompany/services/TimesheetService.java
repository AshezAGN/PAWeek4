package com.mycompany.services;

import com.mycompany.module.Employee;
import com.mycompany.module.Timesheet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public interface TimesheetService extends CrudRepository<Timesheet,Integer> {

    Iterable<Timesheet> findByEmployee(Employee employee);
    Optional<Timesheet> findByEmployeeAndTimeDate(Employee employee, LocalDate date);
}
