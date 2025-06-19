// src/main/java/com/flashcards/web/WebApp.java
package com.flashcards.web;

import com.flashcards.repository.FileFlashcardRepository;
import com.flashcards.repository.FileLessonRepository;
import com.flashcards.repository.FileStatsRepository;
import com.flashcards.repository.FileUserRepository;
import com.flashcards.service.FlashcardService;
import com.flashcards.service.LessonService;
import com.flashcards.service.StatsService;
import com.flashcards.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.flashcards")
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

    @Bean
    public LessonService lessonService() {
        return new LessonService(new FileLessonRepository("data/lessons.json"));
    }

    @Bean
    public FlashcardService flashcardService() {
        return new FlashcardService(new FileFlashcardRepository("data/flashcards.json"));
    }

    @Bean
    public UserService userService() {
        return new UserService(new FileUserRepository("data/users.json"));
    }

    @Bean
    public StatsService statsService() {
        return new StatsService(new FileStatsRepository("data/stats.json"));
    }
}
