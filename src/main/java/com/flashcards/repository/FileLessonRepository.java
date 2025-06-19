package com.flashcards.repository;

import com.flashcards.model.Lesson;
import com.flashcards.util.FileHandler;

import java.io.IOException;
import java.util.List;

public class FileLessonRepository implements Repository<Lesson> {
    private final String filePath;

    public FileLessonRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Lesson> findAll() {
        try {
            return FileHandler.readObjects(filePath, Lesson.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read lessons", e);
        }
    }

    @Override
    public Lesson findById(String id) {
        return findAll().stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Lesson lesson) {
        List<Lesson> list = findAll();
        list.removeIf(l -> l.getId().equals(lesson.getId()));
        list.add(lesson);
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Could not write lessons", e);
        }
    }

    @Override
    public void delete(String id) {
        List<Lesson> list = findAll();
        list.removeIf(l -> l.getId().equals(id));
        try {
            FileHandler.writeObjects(filePath, list);
        } catch (IOException e) {
            throw new RuntimeException("Could not write lessons", e);
        }
    }
}
