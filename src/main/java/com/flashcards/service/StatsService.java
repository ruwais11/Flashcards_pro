package com.flashcards.service;
import com.flashcards.model.StatRecord;

import com.flashcards.model.StatRecord;
import com.flashcards.repository.Repository;

import java.time.Instant;
import java.util.List;

public class StatsService {
    private final Repository<StatRecord> repo;

    public StatsService(Repository<StatRecord> repo) {
        this.repo = repo;
    }

    /** Record one quiz result for a flashcard. */
    public void recordResult(String flashcardId, boolean correct) {
        StatRecord rec = new StatRecord(
                flashcardId,
                Instant.now().toString(),
                correct
        );
        repo.save(rec);
    }

    /** If you need to fetch history later… */
    public List<StatRecord> getAllRecords() {
        return repo.findAll();
    }
}
