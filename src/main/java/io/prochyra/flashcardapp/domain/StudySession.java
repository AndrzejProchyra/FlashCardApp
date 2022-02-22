package io.prochyra.flashcardapp.domain;

import java.util.Iterator;

public class StudySession {
    private final Iterator<Card> cardIterator;
    private int cardCount;

    public StudySession(Deck deck, int cardCount) {
        requireOneOrMoreCards(cardCount);
        cardIterator = deck.iterator();
        this.cardCount = cardCount;
    }

    public Card nextCard() {
        cardCount--;
        return cardIterator.next();
    }

    public boolean hasNextCard() {
        return cardCount > 0;
    }

    private void requireOneOrMoreCards(int cardCount) {
        if (cardCount <= 0) {
            throw new IllegalArgumentException("Study session must have at least one card.");
        }
    }
}
