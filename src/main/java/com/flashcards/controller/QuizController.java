// src/main/java/com/flashcards/controller/QuizController.java
package com.flashcards.controller;

import com.flashcards.model.Flashcard;
import com.flashcards.service.FlashcardService;
import com.flashcards.service.StatsService;

import java.util.List;
import java.util.Scanner;

public class QuizController {
    private final FlashcardService cardService;
    private final StatsService     statsService;
    private final Scanner          scanner;

    public QuizController(
            FlashcardService cardService,
            StatsService statsService,
            Scanner scanner
    ) {
        this.cardService  = cardService;
        this.statsService = statsService;
        this.scanner      = scanner;
    }

    public void runQuiz(String lessonId) {
        List<Flashcard> cards = cardService.getCardsForLesson(lessonId);
        for (Flashcard c : cards) {
            System.out.println("Q: " + c.getQuestion());
            System.out.print("Your answer: ");
            String ans = scanner.nextLine().trim();
            boolean correct = ans.equals(c.getAnswer());
            statsService.recordResult(c.getId(), correct);
            System.out.println(correct ? "✓ Correct" : "✗ No, it was: " + c.getAnswer());
        }
    }
}
