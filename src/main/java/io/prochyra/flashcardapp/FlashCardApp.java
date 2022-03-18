package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.adapter.in.console.ConsoleAdapter;
import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;

import java.util.List;

public class FlashCardApp {
    public static void main(String[] args) {
        // put this into the (InMemory)CardRepository
        Card card1 = new Card("concept1", "definition1");
        Card card2 = new Card("concept2", "definition2");
        List<Card> cards = List.of(card1, card2);

        // create this using the CardRepository and not the list
        Deck deck = new Deck(cards);

        ConsoleAdapter consoleAdapter = new ConsoleAdapter(deck);
        consoleAdapter.start();
    }
}
