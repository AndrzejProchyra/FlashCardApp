package io.prochyra.flashcardapp.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardCreator {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cards", "");
        return "card-creator";
    }
}
