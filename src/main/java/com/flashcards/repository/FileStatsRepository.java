package com.flashcards.repository;

import com.flashcards.model.StatRecord;
import com.flashcards.util.FileHandler;

import java.io.IOException;
import java.util.List;

public class FileStatsRepository implements Repository<StatRecord> {
    private final String filePath;

    public FileStatsRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<StatRecord> findAll() {
        try {
            return FileHandler.readObjects(filePath, StatRecord.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stats", e);
        }
    }

    @Override
    public StatRecord findById(String id) {
        // Fallback: find first matching flashcardId
        return findAll().stream()
                .filter(r -> id.equals(r.getFlashcardId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(StatRecord record) {
        List<StatRecord> all = findAll();
        all.add(record);
        try {
            FileHandler.writeObjects(filePath, all);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write stats", e);
        }
    }

    @Override
    public void delete(String id) {
        List<StatRecord> all = findAll();
        all.removeIf(r -> id.equals(r.getFlashcardId()));
        try {
            FileHandler.writeObjects(filePath, all);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write stats", e);
        }
    }
}
