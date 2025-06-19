// src/main/java/com/flashcards/service/UserService.java
package com.flashcards.service;

import com.flashcards.model.User;
import com.flashcards.repository.Repository;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final Repository<User> repo;

    public UserService(Repository<User> repo) {
        this.repo = repo;
    }

    /** Lookup an existing user. */
    public User getById(String id) {
        return repo.findById(id);
    }

    /** Create a brand-new user with a generated ID. */
    public String createUser(String username) {
        String id = UUID.randomUUID().toString();
        User u = new User(id, username, false);
        repo.save(u);
        return id;
    }

    /** List all users (for admin). */
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    /** Toggle admin flag on a user. */
    public void saveUser(User u) {
        repo.save(u);
    }
}
