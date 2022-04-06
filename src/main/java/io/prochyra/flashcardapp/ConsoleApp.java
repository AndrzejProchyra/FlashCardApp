package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.adapter.in.console.ConsoleAdapter;
import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.application.port.InMemoryCardRepository;
import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;

public class ConsoleApp {
    
    public static void main(String[] args) {
        CardRepository cardRepository = new InMemoryCardRepository();
        Card card1 = new Card("concept1", "definition1");
        Card card2 = new Card("concept2", "definition2");
        cardRepository.save(card1);
        cardRepository.save(card2);

        Deck deck = new Deck(cardRepository.findAll());

        ConsoleAdapter consoleAdapter = new ConsoleAdapter(deck);
        consoleAdapter.start();
    }
}
