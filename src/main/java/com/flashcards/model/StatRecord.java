package com.flashcards.model;

/**
 * A single quiz result for one Flashcard.
 */
public class StatRecord {
    private String flashcardId;
    private String timestamp;
    private boolean correct;

    public StatRecord() {}

    public StatRecord(String flashcardId, String timestamp, boolean correct) {
        this.flashcardId = flashcardId;
        this.timestamp   = timestamp;
        this.correct     = correct;
    }

    public String getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(String flashcardId) {
        this.flashcardId = flashcardId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
