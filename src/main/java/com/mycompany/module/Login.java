package com.mycompany.module;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "logintable")
public class Login {

    @Id
    private int loginID;

    @Column(unique = true, length = 10, nullable = false)
    @NotNull
    private String loginUsername;

    @Column(length = 20, nullable = false)
    @NotNull
    private String loginPassword;

    @Column(length = 10, nullable = false)
    private String loginStatus;

    @OneToOne(targetEntity = Employee.class)
    @MapsId
    private Employee employee;
}
