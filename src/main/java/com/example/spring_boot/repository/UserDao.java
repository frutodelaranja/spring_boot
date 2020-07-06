package com.example.spring_boot.repository;

import com.example.spring_boot.entity.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    User findById(Long id);

    List<User> listUsers();

    void delete(Long id);

    void editUser(User user);

    User findByUsername(String username);
}
