package com.mycompany.services;

import com.mycompany.module.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LoginService extends CrudRepository<Login, Integer> {

    Optional<Login> findByLoginUsername(String loginUsername);

}
