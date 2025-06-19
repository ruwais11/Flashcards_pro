// src/main/java/com/flashcards/ui/ConsoleUI.java
package com.flashcards.ui;

import com.flashcards.controller.*;
import java.util.Scanner;

public class ConsoleUI {
    private final LessonController    lessonCtrl;
    private final FlashcardController cardCtrl;
    private final UserController      userCtrl;
    private final AdminController     adminCtrl;
    private final QuizController      quizCtrl;
    private final Scanner             scanner;
    private final String              currentUserId;
    private final boolean             isAdmin;

    public ConsoleUI(
            LessonController lessonCtrl,
            FlashcardController cardCtrl,
            UserController userCtrl,
            AdminController adminCtrl,
            QuizController quizCtrl,
            Scanner scanner,
            String currentUserId,
            boolean isAdmin
    ) {
        this.lessonCtrl    = lessonCtrl;
        this.cardCtrl      = cardCtrl;
        this.userCtrl      = userCtrl;
        this.adminCtrl     = adminCtrl;
        this.quizCtrl      = quizCtrl;
        this.scanner       = scanner;
        this.currentUserId = currentUserId;
        this.isAdmin       = isAdmin;
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("=== FlashCards Pro ===");
            System.out.println("1) List Lessons    2) Add Lesson    3) Edit Lesson");
            System.out.println("4) List Cards      5) Add Card      6) Edit Card     9) Delete Card");
            System.out.println("7) Take Quiz       8) Manage Users");
            if (isAdmin) {
                System.out.println("A) Review Lessons  B) Review Flashcards");
            }
            System.out.println("0) Exit");
            System.out.print("> ");

            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1" -> lessonCtrl.listLessons();
                case "2" -> lessonCtrl.addLesson();
                case "3" -> lessonCtrl.editLesson();
                case "4" -> {
                    System.out.print("Lesson ID: ");
                    cardCtrl.listCards(scanner.nextLine().trim());
                }
                case "5" -> {
                    System.out.print("Lesson ID: ");
                    cardCtrl.addCard(scanner.nextLine().trim());
                }
                case "6" -> {
                    System.out.print("Lesson ID: ");
                    cardCtrl.editCard(scanner.nextLine().trim());
                }
                case "9" -> {
                    System.out.print("Lesson ID: ");
                    cardCtrl.deleteCard(scanner.nextLine().trim());
                }
                case "7" -> {
                    System.out.print("Lesson ID: ");
                    quizCtrl.runQuiz(scanner.nextLine().trim());
                }
                case "8" -> userCtrl.manageUsers();
                case "A" -> { if (isAdmin) adminCtrl.reviewLessons(); }
                case "B" -> { if (isAdmin) adminCtrl.reviewFlashcards(); }
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
