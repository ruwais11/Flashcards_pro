// src/main/java/com/flashcards/repository/FileFlashcardRepository.java
package com.flashcards.repository;

import com.flashcards.model.Flashcard;
import com.flashcards.util.FileHandler;
import java.io.IOException;
import java.util.List;

public class FileFlashcardRepository implements Repository<Flashcard> {
    private final String filePath;

    public FileFlashcardRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Flashcard> findAll() {
        try {
            return FileHandler.readObjects(filePath, Flashcard.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read flashcards", e);
        }
    }

    @Override
    public Flashcard findById(String id) {
        return findAll().stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Flashcard card) {
        List<Flashcard> list = findAll();
        // remove any existing card with same ID (for update)…
        list.removeIf(c -> c.getId() != null && c.getId().equals(card.getId()));
        list.add(card);
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write flashcards", e);
        }
    }

    @Override
    public void delete(String id) {
        List<Flashcard> list = findAll();
        list.removeIf(c -> c.getId().equals(id));
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write flashcards", e);
        }
    }
}
