package com.anyquestions69.fb.repository;

import com.anyquestions69.fb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(boolean admin);
    List<User> findByEmail(String email);
}
