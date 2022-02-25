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
        return cardsOf(Confidence.LOW);
    }

    public List<Card> unknownConfidenceCards() {
        return cardsOf(Confidence.UNKNOWN);
    }

    private List<Card> cardsOf(Confidence confidence) {
        return cards.stream()
                .filter(card -> card.confidence().equals(confidence))
                .toList();
    }

    public int totalCardCount() {
        return cards.size();
    }

    public long highConfidenceCount() {
        return countForConfidence(Confidence.HIGH);
    }

    public long mediumConfidenceCount() {
        return countForConfidence(Confidence.MEDIUM);
    }

    public long lowConfidenceCount() {
        return countForConfidence(Confidence.LOW);
    }

    private long countForConfidence(Confidence confidence) {
        return cards.stream()
                .filter(card -> card.confidence().equals(confidence))
                .count();
    }
}
