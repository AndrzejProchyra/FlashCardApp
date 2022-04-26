package io.prochyra.flashcardapp.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlashCardController {

    @GetMapping("/flashcard")
    String flashCard(Model model) {
        model.addAttribute("concept", "anystring");
        return "flashcard";
    }
    
    @PostMapping("/start")
    String newSession() {
        return "redirect:";
    }
}
