package com.flashcards.repository;

import com.flashcards.model.Flashcard;
import com.flashcards.util.FileHandler;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FileFlashcardRepository implements Repository<Flashcard> {

    private final String filePath = "data/flashcards.json";

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
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Flashcard card) {
        List<Flashcard> list = findAll();
        // remove any existing with same id
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
        list.removeIf(f -> f.getId().equals(id));
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write flashcards", e);
        }
    }
}
