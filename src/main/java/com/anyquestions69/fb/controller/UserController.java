package com.anyquestions69.fb.controller;

import com.anyquestions69.fb.model.User;
import com.anyquestions69.fb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository ;
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false)String order) {
        try {
            List<User> users = new ArrayList<User>();
            userRepository.findAll().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        try{
            Optional<User> user = userRepository.findById(id);
            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user){
        try {
            Optional<User> _userData = userRepository.findByEmail(user.getEmail());
            if(_userData.isPresent()){
                User _user = _userData.get();
                if(!user.getPassword().isEmpty()) {
                    _user.setPassword(user.getEmail());
                }
                if(!user.getEmail().isEmpty()) {
                    _user.setEmail(user.getEmail());
                }
                if(!user.getPassword().isEmpty()) {
                    _user.setPassword(user.getPassword());
                }
                return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
