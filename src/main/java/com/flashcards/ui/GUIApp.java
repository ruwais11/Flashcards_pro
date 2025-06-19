// src/main/java/com/flashcards/ui/GUIApp.java
package com.flashcards.ui;

import com.flashcards.controller.*;
import com.flashcards.model.User;
import com.flashcards.repository.*;
import com.flashcards.service.*;
import java.util.Scanner;

public class GUIApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Repositories
        Repository<com.flashcards.model.Lesson> lessonRepo   = new FileLessonRepository("data/lessons.json");
        Repository<com.flashcards.model.Flashcard> cardRepo = new FileFlashcardRepository("data/flashcards.json");
        FileUserRepository userRepo                     = new FileUserRepository("data/users.json");
        FileStatsRepository statsRepo                   = new FileStatsRepository("data/stats.json");

        // Services
        LessonService    lessonSvc    = new LessonService(lessonRepo);
        FlashcardService flashcardSvc = new FlashcardService(cardRepo);
        UserService      userSvc      = new UserService(userRepo);
        StatsService     statsSvc     = new StatsService(statsRepo);

        // Controllers
        LessonController    lessonCtrl  = new LessonController(lessonSvc, scanner);
        FlashcardController cardCtrl    = new FlashcardController(flashcardSvc, scanner);
        UserController      userCtrl    = new UserController(userSvc, scanner);
        AdminController     adminCtrl   = new AdminController(lessonSvc, flashcardSvc, scanner);
        QuizController      quizCtrl    = new QuizController(flashcardSvc, statsSvc, scanner);

        // Login / registration
        System.out.print("Enter your user ID (or type NEW to register): ");
        String userId = scanner.nextLine().trim();
        if ("NEW".equalsIgnoreCase(userId)) {
            userId = userCtrl.registerUser();
        }
        User you = userSvc.getById(userId);
        boolean isAdmin = you != null && you.isAdmin();

        // Launch the console UI
        new ConsoleUI(
                lessonCtrl,
                cardCtrl,
                userCtrl,
                adminCtrl,
                quizCtrl,
                scanner,
                userId,
                isAdmin
        ).start();
    }
}
