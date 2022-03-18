package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckConfidenceOrderTest {

    @Test
    void deckWithCardsInRandomOrder_ReturnsCardsInReverseConfidenceOrder() {
        Card unknown = new Card("a", "a");
        Card low = new Card("b", "b", Confidence.LOW);
        Card medium = new Card("c", "c", Confidence.MEDIUM);
        Card high = new Card("d", "d", Confidence.HIGH);
        Deck deck = new Deck(List.of(medium, low, high, unknown));

        Iterator<Card> deckIterator = deck.iterator();

        assertThat(deckIterator.next().confidence())
                .isEqualTo(Confidence.UNKNOWN);
        assertThat(deckIterator.next().confidence())
                .isEqualTo(Confidence.LOW);
        assertThat(deckIterator.next().confidence())
                .isEqualTo(Confidence.MEDIUM);
        assertThat(deckIterator.next().confidence())
                .isEqualTo(Confidence.HIGH);
    }
}
