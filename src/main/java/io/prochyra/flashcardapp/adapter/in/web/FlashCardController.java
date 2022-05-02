package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.StudySession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlashCardController {
    private final StudySession studySession;
    Card card = new Card("first concept", "first definition");

    public FlashCardController(StudySession studySession) {
        this.studySession = studySession;
    }

    @GetMapping("/flashcard")
    String flashCard(Model model) {
        model.addAttribute("content", card.content());
        model.addAttribute("showFlip", !card.isFlipped());
        return "flashcard";
    }

    @GetMapping("/")
    String home() {
        return "start";
    }

    @PostMapping("/start")
    String newSession() {
        card = studySession.nextCard();
        return "redirect:/flashcard";
    }

    @PostMapping("/flip")
    String flip() {
        card.flip();
        return "redirect:/flashcard";
    }

    @PostMapping("/confidence")
    String recordConfidence() {
        card = studySession.nextCard();
        return "redirect:/flashcard";
    }
}
