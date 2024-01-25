package com.anyquestions69.fb.repository;

import com.anyquestions69.fb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findOneByEmail(String email);
}
