package com.mycompany.module;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "employeetable")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;
    @Column(length = 25, nullable = false)
    private String empName;
    @Column(length = 10, nullable = false)
    private String empContact ;
    @Column(length = 25)
    private String empDepartment;
    @Column(length = 30)
    @Email(message = "Missing/Invalid Email")
    private String empEmail;
    @Column(length = 50)
    private String empAddress;
    @Column(length = 10, nullable = false)
    private String empRole;


}
