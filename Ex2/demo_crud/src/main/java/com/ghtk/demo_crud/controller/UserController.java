package com.ghtk.demo_crud.controller;

import com.ghtk.demo_crud.exception.NotFoundException;
import com.ghtk.demo_crud.model.ResponseObject;
import com.ghtk.demo_crud.model.Users;
import com.ghtk.demo_crud.repository.UserRepository;
import com.ghtk.demo_crud.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/Users")
    public ResponseEntity<ResponseObject> getAllUsers() {
        List<Users> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok","Successfully",users)
        );
    }


    @GetMapping("/Users/{Id}")
    ResponseEntity<ResponseObject> getUserById(@PathVariable Integer Id) {
        Optional<Users> user = this.userService.findById(Id);
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok","Query successfully",user)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed","can't not find user have id = "+Id,"")
            );
        }
    }
    @PostMapping("/Users/create")
    ResponseEntity<ResponseObject> createUSer(@RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("Ok","Create user successfully",this.userService.create(user))
        );
    }
    @PutMapping("/Users/{Id}")
    ResponseEntity<ResponseObject> updateUSer(@RequestBody Users user,@PathVariable Integer Id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok","Update user successfully",this.userService.update(user,Id))
        );
    }
    @DeleteMapping("/Users/{Id}")
    public void deleteUser(@PathVariable Integer Id) {
        userService.delete(Id);
    }

//    List<String> getAllUsers() {return List.of("user1","user2");}
}
