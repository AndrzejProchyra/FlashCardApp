package io.prochyra.flashcardapp.application.port;

import io.prochyra.flashcardapp.domain.Card;

import java.util.ArrayList;
import java.util.List;

// TODO: extract CardRepository interface
public class InMemoryCardRepository {
    private final List<Card> cards = new ArrayList<>();

    public List<Card> findAll() {
        return List.copyOf(cards);
    }

    public Card save(Card card) {
        this.cards.add(card);
        return null;
    }
}
