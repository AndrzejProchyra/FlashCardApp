package io.prochyra.flashcardapp.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlashCardController {
    
    @GetMapping("/flashcard")
    void flashCard() {
        
    }
}