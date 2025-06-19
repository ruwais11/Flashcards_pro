// src/main/java/com/flashcards/controller/FlashcardController.java
package com.flashcards.controller;

import com.flashcards.model.Flashcard;
import com.flashcards.service.FlashcardService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FlashcardController {
    private final FlashcardService cardService;
    private final Scanner          scanner;

    public FlashcardController(FlashcardService cardService, Scanner scanner) {
        this.cardService = cardService;
        this.scanner     = scanner;
    }

    public void listCards(String lessonId) {
        System.out.println("=== Cards in lesson " + lessonId + " ===");
        List<Flashcard> cards = cardService.getCardsForLesson(lessonId);
        for (Flashcard c : cards) {
            System.out.printf("[%s] %s → %s%n",
                    c.getId(), c.getQuestion(), c.getAnswer());
        }
    }

    public void addCard(String lessonId) {
        System.out.print("Question: ");
        String q = scanner.nextLine().trim();
        System.out.print("Answer: ");
        String a = scanner.nextLine().trim();
        Flashcard c = new Flashcard(UUID.randomUUID().toString(), lessonId, q, a);
        cardService.saveCard(c);
        System.out.println("Added.");
    }

    public void editCard(String lessonId) {
        System.out.print("Card ID to edit: ");
        String id = scanner.nextLine().trim();
        Flashcard c = cardService.getCardById(id);
        if (c == null) {
            System.out.println("Not found.");
            return;
        }
        System.out.print("New question (" + c.getQuestion() + "): ");
        String q = scanner.nextLine().trim();
        if (!q.isEmpty()) c.setQuestion(q);
        System.out.print("New answer (" + c.getAnswer() + "): ");
        String a = scanner.nextLine().trim();
        if (!a.isEmpty()) c.setAnswer(a);
        cardService.saveCard(c);
        System.out.println("Saved.");
    }

    public void deleteCard(String lessonId) {
        System.out.print("Card ID to delete: ");
        String id = scanner.nextLine().trim();
        cardService.deleteCard(id);
        System.out.println("Deleted.");
    }
}
