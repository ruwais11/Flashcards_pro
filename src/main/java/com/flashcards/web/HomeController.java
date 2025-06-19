// src/main/java/com/flashcards/web/HomeController.java
package com.flashcards.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        // Redirect browser to /lessons
        return "redirect:/lessons";
    }
}
