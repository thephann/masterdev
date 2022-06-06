package com.ghtk.demo_crud.service;


import com.ghtk.demo_crud.model.Users;
import org.hibernate.service.Service;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service {
    List<Users> getAll();
    Optional<Users> findById(Integer Id);

    Users create(Users user);

    Users save(Users user);

    Users update(Users user,Integer Id);

    void delete(Integer Id);
}
