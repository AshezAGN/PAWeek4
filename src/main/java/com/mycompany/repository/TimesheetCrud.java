package com.mycompany.repository;

import com.mycompany.module.Login;
import com.mycompany.module.Timesheet;
import org.springframework.data.repository.CrudRepository;
//TODO : CONVERT SERVICE TO THIS.
public interface TimesheetCrud extends CrudRepository<Timesheet, Integer> {

}