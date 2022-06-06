package com.ghtk.demo_crud.repository;

import com.ghtk.demo_crud.model.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users save(Users user);
    List<Users> findByEmail(String email);
    void deleteById(Integer Id);
}
