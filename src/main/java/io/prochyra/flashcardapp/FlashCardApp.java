package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.adapter.in.console.ConsoleAdapter;
import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;

import java.util.List;

public class FlashCardApp {
    public static void main(String[] args) {
        Card card1 = new Card("concept1", "definition1");
        Card card2 = new Card("concept2", "definition2");
        List<Card> cards = List.of(card1, card2);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(new Deck(cards));
        consoleAdapter.start();
    }
}
