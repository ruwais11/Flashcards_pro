package com.flashcards.service;

import com.flashcards.model.Lesson;
import com.flashcards.repository.Repository;

import java.util.List;

public class LessonService {
    private final Repository<Lesson> repo;

    // Inject the repository via constructor
    public LessonService(Repository<Lesson> repo) {
        this.repo = repo;
    }

    /** Get all lessons */
    public List<Lesson> getAllLessons() {
        return repo.findAll();
    }

    /** Find one lesson by ID */
    public Lesson getLessonById(String id) {
        return repo.findById(id);
    }

    /** Create or update a lesson */
    public void saveLesson(Lesson lesson) {
        repo.save(lesson);
    }

    /** Delete a lesson */
    public void deleteLesson(String id) {
        repo.delete(id);
    }
}
