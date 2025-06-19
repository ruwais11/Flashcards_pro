// src/main/java/com/flashcards/web/AdminController.java
package com.flashcards.web;

import com.flashcards.model.Flashcard;
import com.flashcards.service.FlashcardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final FlashcardService cardSvc;

    public AdminController(FlashcardService cardSvc) {
        this.cardSvc = cardSvc;
    }

    @GetMapping("/admin/cards")
    public String allCards(Model model) {
        List<Flashcard> cards = cardSvc.getAllCards();
        model.addAttribute("cards", cards);
        return "admin/cards";  // make sure you have src/main/resources/templates/admin/cards.html
    }
}
