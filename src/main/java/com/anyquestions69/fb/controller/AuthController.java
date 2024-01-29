package com.anyquestions69.fb.controller;

import com.anyquestions69.fb.model.User;
import com.anyquestions69.fb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User _user = userRepository.save(new User(user.getEmail(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            User _user = userRepository.findOneByEmail(user.getEmail());
            if(Objects.equals(_user.getPassword(), user.getPassword())){
                return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("IncorrectPassword", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/login")
    public String loginForm(Model model){
        return "login";
    }
}
