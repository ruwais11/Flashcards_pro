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

    // LIST all cards for a lesson
    @GetMapping
    public String list(@PathVariable String lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("cards", flashcardService.getCardsForLesson(lessonId));
        return "flashcards/list";
    }

    // SHOW the “new card” form
    @GetMapping("/new")
    public String createForm(@PathVariable String lessonId, Model model) {
        Flashcard card = new Flashcard();
        card.setLessonId(lessonId);
        model.addAttribute("card", card);
        return "flashcards/form";
    }

    // HANDLE submission of the “new card” form
    @PostMapping
    public String create(@PathVariable String lessonId,
                         @ModelAttribute("card") Flashcard card) {
        card.setLessonId(lessonId);
        flashcardService.saveCard(card);
        return "redirect:/lessons/" + lessonId + "/cards";
    }

    // SHOW the “edit card” form
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String lessonId,
                           @PathVariable String id,
                           Model model) {
        Flashcard card = flashcardService.getCardById(id);
        model.addAttribute("card", card);
        return "flashcards/form";
    }

    // HANDLE submission of the “edit card” form
    @PostMapping("/edit/{id}")
    public String update(@PathVariable String lessonId,
                         @PathVariable String id,
                         @ModelAttribute("card") Flashcard card) {
        card.setId(id);
        card.setLessonId(lessonId);
        flashcardService.saveCard(card);
        return "redirect:/lessons/" + lessonId + "/cards";
    }

    // DELETE a card
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String lessonId,
                         @PathVariable String id) {
        flashcardService.deleteCard(id);
        return "redirect:/lessons/" + lessonId + "/cards";
    }
}
