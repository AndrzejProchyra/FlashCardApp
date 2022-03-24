package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.application.port.CardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CardCreator {

    private final CardRepository repository;

    public CardCreator(CardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<CardView> cardViews = repository.findAll()
                .stream()
                .map(CardView::from)
                .toList();
        model.addAttribute("cards", cardViews);
        model.addAttribute("cardForm", new CardForm());
        return "card-creator";
    }

    @PostMapping("/")
    public String createCard(@ModelAttribute CardForm cardForm) {
        repository.save(cardForm.toCard());
        return "redirect:/";
    }
}
