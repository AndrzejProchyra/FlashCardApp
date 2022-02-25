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

    public int totalCardCount() {
        return cards.size();
    }

    public int highConfidenceCount() {
        return countForConfidence(Confidence.HIGH);
    }

    public int mediumConfidenceCount() {
        return countForConfidence(Confidence.MEDIUM);
    }

    public int lowConfidenceCount() {
        return countForConfidence(Confidence.LOW);
    }

    private int countForConfidence(Confidence confidence) {
        return (int) cards.stream()
                .filter(card -> card.confidence().equals(confidence))
                .count();
    }
}
