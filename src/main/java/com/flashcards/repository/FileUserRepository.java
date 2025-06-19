package com.flashcards.repository;

import com.flashcards.model.User;
import com.flashcards.util.FileHandler;

import java.io.IOException;
import java.util.List;

public class FileUserRepository implements Repository<User> {
    private final String filePath;

    public FileUserRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<User> findAll() {
        try {
            return FileHandler.readObjects(filePath, User.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read users", e);
        }
    }

    @Override
    public User findById(String id) {
        return findAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        List<User> list = findAll();
        list.removeIf(u -> u.getId().equals(user.getId()));
        list.add(user);
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write users", e);
        }
    }

    @Override
    public void delete(String id) {
        List<User> list = findAll();
        list.removeIf(u -> u.getId().equals(id));
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write users", e);
        }
    }

    public User findByUsername(String username) {
        return findAll().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}
