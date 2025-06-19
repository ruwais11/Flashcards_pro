package com.flashcards.model;

public class Lesson {
    private String id;
    private String title;

    // Jackson needs a no-arg constructor for deserialization
    public Lesson() {}

    public Lesson(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Lesson{id='" + id + "', title='" + title + "'}";
    }
}
