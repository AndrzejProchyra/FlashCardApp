package io.prochyra.flashcardapp.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlashCardController {

    @GetMapping("/flashcard")
    String flashCard(Model model) {
        model.addAttribute("concept", "anystring");
        return "flashcard";
    }

    @GetMapping("/start")
    String start() {
        return "start";
    }
}
