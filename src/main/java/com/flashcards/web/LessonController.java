package com.flashcards.web;

import com.flashcards.model.Lesson;
import com.flashcards.service.LessonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    // List all lessons
    @GetMapping
    public String list(Model model) {
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "lessons/list";
    }

    // Show form to create a new lesson
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "lessons/form";
    }

    // Handle form submission for both create & edit
    @PostMapping
    public String save(
            @Valid @ModelAttribute("lesson") Lesson lesson,
            BindingResult binding
    ) {
        if (binding.hasErrors()) {
            return "lessons/form";
        }
        lessonService.saveLesson(lesson);
        return "redirect:/lessons";
    }

    // Show form to edit an existing lesson
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        Lesson existing = lessonService.getLessonById(id);
        model.addAttribute("lesson", existing);
        return "lessons/form";
    }
}
