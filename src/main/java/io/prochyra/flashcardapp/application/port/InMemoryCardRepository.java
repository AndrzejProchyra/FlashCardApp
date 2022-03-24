package io.prochyra.flashcardapp.application.port;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.CardId;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCardRepository implements CardRepository {
    private final Map<CardId, Card> cardMap = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public List<Card> findAll() {
        return List.copyOf(cardMap.values());
    }

    @Override
    public Card save(Card card) {
        if (card.getId() == null) {
            card.setId(CardId.of(sequence.getAndIncrement()));
        }
        cardMap.put(card.getId(), card);
        return card;
    }
}
