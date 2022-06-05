package com.ghtk.demo_crud.service;

import com.ghtk.demo_crud.exception.DuplicateRecordException;
import com.ghtk.demo_crud.model.Users;
import com.ghtk.demo_crud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<Users> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Integer Id) {
        return this.userRepository.findById(Id);
    }

    @Override
    public Users save(Users user) {
        this.userRepository.save(user);
        return user;
    }

    @Override
    public Users create(Users user) {
        List<Users> foundUser = this.userRepository.findById(user.getId()).stream().collect(Collectors.toList());
        List<Users> foundUserByE = this.userRepository.findByEmail(user.getEmail().trim());

        if (foundUser.size()>0 || foundUserByE.size()>0) {
            throw new DuplicateRecordException("User already exist");
        }
        this.save(user);

        return user;
    }
}
