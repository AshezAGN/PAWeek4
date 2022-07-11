package com.mycompany.module;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "timesheettable")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timesheetId;
    @Column(unique = true)
    private LocalDate timeDate;
    @PositiveOrZero
    @Max(24)
    private int timeTotalHours;
    @Column(length = 20)
    private String timeTask;
    @Column(length = 50)
    private String timeDescription;
    @ManyToOne(targetEntity = Employee.class)
    private Employee employee;
}
