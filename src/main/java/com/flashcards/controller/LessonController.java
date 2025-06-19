// src/main/java/com/flashcards/controller/LessonController.java
package com.flashcards.controller;

import com.flashcards.model.Lesson;
import com.flashcards.service.LessonService;

import java.util.List;
import java.util.Scanner;

public class LessonController {
    private final LessonService service;
    private final Scanner       scanner;

    public LessonController(LessonService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void listLessons() {
        System.out.println("=== Lessons ===");
        List<Lesson> all = service.getAllLessons();
        for (Lesson l : all) {
            System.out.printf("%s: %s%n", l.getId(), l.getTitle());
        }
    }

    /** No-arg addLesson for ConsoleUI */
    public void addLesson() {
        System.out.print("New lesson ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("New lesson title: ");
        String title = scanner.nextLine().trim();
        service.saveLesson(new Lesson(id, title));
        System.out.println("Lesson saved.");
    }

    public void editLesson() {
        System.out.print("Lesson ID to edit: ");
        String id = scanner.nextLine().trim();
        Lesson l = service.getLessonById(id);
        if (l == null) {
            System.out.println("Not found.");
            return;
        }
        System.out.printf("Current title [%s], new title: ", l.getTitle());
        String t2 = scanner.nextLine().trim();
        if (!t2.isEmpty()) {
            l.setTitle(t2);
            service.saveLesson(l);
            System.out.println("Lesson updated.");
        }
    }

    public void deleteLesson(String lessonId) {
        service.deleteLesson(lessonId);
        System.out.println("Deleted lesson " + lessonId);
    }
}
