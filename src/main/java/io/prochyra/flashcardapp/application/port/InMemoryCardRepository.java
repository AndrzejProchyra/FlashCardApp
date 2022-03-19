package io.prochyra.flashcardapp.application.port;

import io.prochyra.flashcardapp.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCardRepository implements CardRepository {
    private final List<Card> cards = new ArrayList<>();

    @Override
    public List<Card> findAll() {
        return List.copyOf(cards);
    }

    @Override
    public Card save(Card card) {
        this.cards.add(card);
        return card;
    }
}
