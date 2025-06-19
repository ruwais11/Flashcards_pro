// src/main/java/com/flashcards/ui/QuizDialog.java
package com.flashcards.ui;

import com.flashcards.model.Flashcard;
import com.flashcards.service.FlashcardService;

import java.util.List;
import java.util.Scanner;

public class QuizDialog {
    private final FlashcardService cardService;
    private final Scanner scanner;

    public QuizDialog(FlashcardService cardService, Scanner scanner) {
        this.cardService = cardService;
        this.scanner     = scanner;
    }

    public void runForLesson(String lessonId) {
        List<Flashcard> cards = cardService.getCardsForLesson(lessonId);  // <— updated here
        for (Flashcard c : cards) {
            System.out.println("Q: " + c.getQuestion());
            System.out.print("Your answer: ");
            String ans = scanner.nextLine().trim();
            System.out.println(ans.equals(c.getAnswer()) ? "✓ Correct" : "✗ No, it was: " + c.getAnswer());
        }
    }
}
