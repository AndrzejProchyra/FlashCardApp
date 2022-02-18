package io.prochyra.flashcardapp.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Deck implements Iterable<Card> {
    public final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public void forEach(Consumer<? super Card> action) {
        cards.forEach(action);
    }

    @Override
    public Spliterator<Card> spliterator() {
        return cards.spliterator();
    }

    public List<Card> lowConfidenceCards() {
        return cards.stream()
                .filter(card -> card.confidence().equals(Confidence.LOW))
                .toList();
    }

    public List<Card> unknownConfidenceCards() {
        return cards.stream()
                .filter(card -> card.confidence().equals(Confidence.UNKNOWN))
                .toList();
    }

    public long highConfidenceCount() {
        return cards.stream()
                .filter(card -> card.confidence().equals(Confidence.HIGH))
                .count();
    }
}
