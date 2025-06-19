package com.flashcards.model;

public class Flashcard {
    private String id;
    private String question;
    private String answer;
    private String lessonId;

    // ⬇️ This no-arg constructor is REQUIRED for data binding
    public Flashcard() { }

    // Your existing all-args constructor
    public Flashcard(String id, String question, String answer, String lessonId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.lessonId = lessonId;
    }

    // Getters & setters for all fields:
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getLessonId() { return lessonId; }
    public void setLessonId(String lessonId) { this.lessonId = lessonId; }
}
