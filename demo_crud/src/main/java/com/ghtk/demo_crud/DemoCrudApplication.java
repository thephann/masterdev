package com.ghtk.demo_crud;

import com.ghtk.demo_crud.model.Users;
import com.ghtk.demo_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCrudApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(DemoCrudApplication.class, args); // create and push all dependency in container
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args)  {
        Users user1 = new Users();
        user1.setEmail("123@ghtk.com");
        user1.setPassword("123");
        userRepository.save(user1);

        Users user2 = new Users();
        user1.setEmail("456@ghtk.com");
        user1.setPassword("456");
        userRepository.save(user2);
    }
}
