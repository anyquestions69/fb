package com.anyquestions69.fb.model;
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name= "admin")
    private boolean admin=false;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", pass=" + password + ", admin=" + admin + "]";
    }
}
