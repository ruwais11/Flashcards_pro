// src/main/java/com/flashcards/controller/UserController.java
package com.flashcards.controller;

import com.flashcards.model.User;
import com.flashcards.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserService service;
    private final Scanner     scanner;

    public UserController(UserService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /** Called at startup if user types NEW */
    public String registerUser() {
        System.out.print("Choose a username: ");
        String name = scanner.nextLine().trim();
        String id = service.createUser(name);
        System.out.println("Registered. Your user ID is " + id);
        return id;
    }

    /** Admin UI to list and promote/demote users */
    public void manageUsers() {
        System.out.println("=== All Users ===");
        List<User> all = service.getAllUsers();
        for (User u : all) {
            System.out.printf("%s %s (admin=%s)%n",
                    u.getId(), u.getUsername(), u.isAdmin());
        }
        System.out.print("Enter user ID to toggle admin (or blank to cancel): ");
        String choice = scanner.nextLine().trim();
        if (!choice.isEmpty()) {
            User u = service.getById(choice);
            if (u != null) {
                u.setAdmin(!u.isAdmin());
                service.saveUser(u);
                System.out.printf("%s is now admin=%s%n", u.getUsername(), u.isAdmin());
            } else {
                System.out.println("No such user.");
            }
        }
    }
}
