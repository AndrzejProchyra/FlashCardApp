package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.adapter.in.console.ConsoleAdapter;
import io.prochyra.flashcardapp.domain.Card;

import java.io.IOException;

public class FlashCardApp {
    public static void main(String[] args) throws IOException {
        Card card = new Card("concept", "definition");
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(card);
        consoleAdapter.start();
    }
}
