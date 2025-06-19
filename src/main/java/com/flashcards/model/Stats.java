package com.flashcards.model;

public class Stats {
    private String userId;
    private String lessonId;
    private int correctCount;
    private int wrongCount;

    // Jackson needs this
    public Stats() { }

    public Stats(String userId, String lessonId, int correctCount, int wrongCount) {
        this.userId = userId;
        this.lessonId = lessonId;
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLessonId() { return lessonId; }
    public void setLessonId(String lessonId) { this.lessonId = lessonId; }

    public int getCorrectCount() { return correctCount; }
    public void setCorrectCount(int correctCount) { this.correctCount = correctCount; }

    public int getWrongCount() { return wrongCount; }
    public void setWrongCount(int wrongCount) { this.wrongCount = wrongCount; }

    @Override
    public String toString() {
        return "Stats{" +
                "userId='" + userId + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", correctCount=" + correctCount +
                ", wrongCount=" + wrongCount +
                '}';
    }
}
