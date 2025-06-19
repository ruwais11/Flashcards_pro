// src/main/java/com/flashcards/service/FlashcardService.java
package com.flashcards.service;

import com.flashcards.model.Flashcard;
import com.flashcards.repository.Repository;
import java.util.List;
import java.util.stream.Collectors;

public class FlashcardService {
    private final Repository<Flashcard> repo;

    public FlashcardService(Repository<Flashcard> repo) {
        this.repo = repo;
    }

    /** For your admin screen */
    public List<Flashcard> getAllCards() {
        return repo.findAll();
    }

    /** For lesson-scoped listing and edit */
    public List<Flashcard> getCardsForLesson(String lessonId) {
        return repo.findAll().stream()
                .filter(c -> c.getLessonId().equals(lessonId))
                .collect(Collectors.toList());
    }

    public Flashcard getCardById(String id) {
        return repo.findById(id);
    }

    public void saveCard(Flashcard card) {
        repo.save(card);
    }

    public void deleteCard(String id) {
        repo.delete(id);
    }
}
