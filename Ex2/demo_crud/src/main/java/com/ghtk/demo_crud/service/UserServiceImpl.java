package com.ghtk.demo_crud.service;

import com.ghtk.demo_crud.exception.DuplicateRecordException;
import com.ghtk.demo_crud.exception.NotFoundException;
import com.ghtk.demo_crud.model.Users;
import com.ghtk.demo_crud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setEmail(user.getEmail());
        user.setId(user.getId());

        return user;
    }

    @Override
    public Users update(Users user,Integer Id)  {

        List<Users> foundUser = this.userRepository.findById(user.getId()).stream().collect(Collectors.toList());
        if (foundUser.size() <1)  {
            throw new NotFoundException("User not found");

        }
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setEmail(user.getEmail());
        user.setId(user.getId());
        return user;
    }

    @Override
    public void delete(Integer Id) {
        Optional<Users> foundUser = userRepository.findById(Id);
        if (!foundUser.isPresent()) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(Id);
    }
}
