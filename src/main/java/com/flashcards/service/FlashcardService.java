package com.flashcards.service;

import com.flashcards.model.Flashcard;
import com.flashcards.repository.FileFlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlashcardService {

    private final FileFlashcardRepository repo;

    public FlashcardService(FileFlashcardRepository repo) {
        this.repo = repo;
    }

    public List<Flashcard> getCardsForLesson(String lessonId) {
        return repo.findAll().stream()
                .filter(c -> lessonId.equals(c.getLessonId()))
                .collect(Collectors.toList());
    }

    public Flashcard getCardById(String id) {
        Flashcard c = repo.findById(id);
        if (c == null) throw new IllegalArgumentException("No flashcard with id " + id);
        return c;
    }

    public void saveCard(Flashcard card) {
        if (card.getId() == null) {
            card.setId(UUID.randomUUID().toString());
        }
        repo.save(card);
    }

    public void deleteCard(String id) {
        repo.delete(id);
    }
}
