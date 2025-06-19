// src/main/java/com/flashcards/controller/AdminController.java
package com.flashcards.controller;

import com.flashcards.model.Flashcard;
import com.flashcards.model.Lesson;
import com.flashcards.service.FlashcardService;
import com.flashcards.service.LessonService;

import java.util.Scanner;

public class AdminController {
    private final LessonService lessonSvc;
    private final FlashcardService cardSvc;
    private final Scanner scanner;

    public AdminController(LessonService lessonSvc, FlashcardService cardSvc, Scanner scanner) {
        this.lessonSvc = lessonSvc;
        this.cardSvc    = cardSvc;
        this.scanner    = scanner;
    }

    public void reviewFlashcards() {
        System.out.println("=== All Flashcards (Admin) ===");
        for (Flashcard c : cardSvc.getAllCards()) {   // <— updated here
            System.out.printf("[%s] %s → %s (lesson %s)%n",
                    c.getId(), c.getQuestion(), c.getAnswer(), c.getLessonId());
        }
        System.out.print("Enter card ID to DELETE or blank to skip: ");
        String id = scanner.nextLine().trim();
        if (!id.isEmpty()) {
            cardSvc.deleteCard(id);
            System.out.println("Deleted!");
        }
    }

    public void reviewLessons() {
        System.out.println("=== All Lessons (Admin) ===");
        for (Lesson l : lessonSvc.getAllLessons()) {
            System.out.printf("[%s] %s%n", l.getId(), l.getTitle());
        }
        // ... etc ...
    }
}
