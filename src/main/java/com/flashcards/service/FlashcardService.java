package com.flashcards.service;

import com.flashcards.model.Flashcard;
import com.flashcards.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlashcardService {
    private final Repository<Flashcard> repo;

    public FlashcardService(Repository<Flashcard> repo) {
        this.repo = repo;
    }

    /** All cards, across every lesson */
    public List<Flashcard> getAllCards() {
        return repo.findAll();
    }

    /** Only the cards for a single lesson */
    public List<Flashcard> getCardsForLesson(String lessonId) {
        return repo.findAll().stream()
                .filter(c -> lessonId.equals(c.getLessonId()))
                .collect(Collectors.toList());
    }

    /** Fetch one card by its ID (throws if missing) */
    public Flashcard getCardById(String id) {
        return Optional.ofNullable(repo.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("No flashcard: " + id));
    }

    /** Create or update */
    public void saveCard(Flashcard card) {
        repo.save(card);
    }

    /** Delete */
    public void deleteCard(String id) {
        repo.delete(id);
    }
}
