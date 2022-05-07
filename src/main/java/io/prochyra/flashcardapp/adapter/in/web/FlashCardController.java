package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.StudySession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlashCardController {
    private StudySession studySession;

    public FlashCardController(StudySession studySession) {
        this.studySession = studySession;
        studySession.nextCard();
    }

    @GetMapping("/flashcard")
    String flashCard(Model model) {
        Card card = studySession.currentCard();
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
        return "redirect:/flashcard";
    }

    @PostMapping("/flip")
    String flip() {
        studySession.currentCard().flip();
        return "redirect:/flashcard";
    }

    @PostMapping("/confidence")
    String recordConfidence() {
        return "redirect:/flashcard";
    }
}
