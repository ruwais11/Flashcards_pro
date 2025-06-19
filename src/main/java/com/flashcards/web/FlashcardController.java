// src/main/java/com/flashcards/web/FlashcardController.java
package com.flashcards.web;

import com.flashcards.model.Flashcard;
import com.flashcards.service.FlashcardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons/{lessonId}/cards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping
    public String list(@PathVariable String lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("cards", flashcardService.getCardsForLesson(lessonId));
        return "flashcards/list";
    }

    @GetMapping("/new")
    public String createForm(@PathVariable String lessonId, Model model) {
        model.addAttribute("card", new Flashcard(null, lessonId, "", ""));
        return "flashcards/form";
    }

    @PostMapping
    public String create(@ModelAttribute Flashcard card) {
        flashcardService.saveCard(card);
        return "redirect:/lessons/" + card.getLessonId() + "/cards";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String lessonId,
                           @PathVariable String id,
                           Model model) {
        Flashcard card = flashcardService.getCardsForLesson(lessonId).stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid card ID: " + id));
        model.addAttribute("card", card);
        return "flashcards/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute Flashcard card) {
        flashcardService.saveCard(card);
        return "redirect:/lessons/" + card.getLessonId() + "/cards";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String lessonId,
                         @PathVariable String id) {
        flashcardService.deleteCard(id);
        return "redirect:/lessons/" + lessonId + "/cards";
    }
}
