package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.application.AddCardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CardCreator {

    private final CardRepository cardRepository;
    private final AddCardService addCardService;

    @Autowired
    public CardCreator(CardRepository cardRepository, AddCardService addCardService) {
        this.addCardService = addCardService;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/create")
    public String homePage(Model model) {
        List<CardView> cardViews = cardRepository.findAll()
                .stream()
                .map(CardView::from)
                .toList();
        model.addAttribute("cards", cardViews);
        model.addAttribute("cardForm", new CardForm());
        return "card-creator";
    }

    @PostMapping("/create")
    public String createCard(@ModelAttribute CardForm cardForm) {
        addCardService.add(cardForm.getConcept(), cardForm.getDefinition());
        return "redirect:/create";
    }
}
