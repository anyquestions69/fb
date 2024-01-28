package com.anyquestions69.fb.controller;

import com.anyquestions69.fb.model.User;
import com.anyquestions69.fb.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public class ViewController {
    UserRepository userRepository;
    @GetMapping("/index")
    public String main(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();

        model.put("users", users);

        return "index ";
    }
}
