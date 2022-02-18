package io.prochyra.flashcardapp.domain;

import java.util.Iterator;

public class StudySession {
    private final Deck deck;
    private final Iterator<Card> cardIterator;

    public StudySession(Deck deck, int cardCount) {
        this.deck = deck;
        cardIterator = deck.iterator();
    }

    public Card nextCard() {
        return cardIterator.next();
    }
}
