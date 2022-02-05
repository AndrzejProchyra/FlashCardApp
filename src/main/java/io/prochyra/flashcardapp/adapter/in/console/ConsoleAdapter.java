package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Card;

import java.io.IOException;

public class ConsoleAdapter {

    private final Card card;

    public ConsoleAdapter(Card card) {
        this.card = card;
    }

    public void start() throws IOException {
        System.out.println(card.content());
        System.in.read();
        card.flip();
        System.out.println(card.content());
    }
}
