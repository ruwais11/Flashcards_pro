package com.flashcards.web;

import com.flashcards.model.Flashcard;
import com.flashcards.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("cards", flashcardService.getCardsForLesson(lessonId));
        model.addAttribute("lessonId", lessonId);
        return "flashcards/list";
    }

    @GetMapping("/new")
    public String createForm(@PathVariable String lessonId, Model model) {
        Flashcard card = new Flashcard();
        card.setLessonId(lessonId);
        model.addAttribute("card", card);
        return "flashcards/form";
    }

    @PostMapping
    public String create(@PathVariable String lessonId,
                         @ModelAttribute("card") @Valid Flashcard card,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "flashcards/form";
        }
        flashcardService.saveCard(card);
        return "redirect:/lessons/" + lessonId + "/cards";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String lessonId,
                           @PathVariable String id,
                           Model model) {
        Flashcard card = flashcardService.getCardById(id);
        model.addAttribute("card", card);
        return "flashcards/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String lessonId,
                         @PathVariable String id,
                         @ModelAttribute("card") @Valid Flashcard card,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "flashcards/form";
        }
        flashcardService.saveCard(card);
        return "redirect:/lessons/" + lessonId + "/cards";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String lessonId,
                         @PathVariable String id) {
        flashcardService.deleteCard(id);
        return "redirect:/lessons/" + lessonId + "/cards";
    }
}
