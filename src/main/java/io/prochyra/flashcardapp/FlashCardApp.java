package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.adapter.in.console.ConsoleAdapter;
import io.prochyra.flashcardapp.domain.Card;

import java.io.IOException;
import java.util.List;

public class FlashCardApp {
    public static void main(String[] args) throws IOException {
        Card card1 = new Card("concept1", "definition1");
        Card card2 = new Card("concept2", "definition2");
        List<Card> deck = List.of(card1, card2);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(deck);
        consoleAdapter.start();
    }
}
