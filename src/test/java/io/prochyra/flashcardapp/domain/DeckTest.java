package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    void returnsLowConfidenceCards() {
        Card lowConfidenceCard = new Card("concept", "definition");
        lowConfidenceCard.flip();
        lowConfidenceCard.recordConfidence(Confidence.LOW);
        Card highConfidenceCard = new Card("concept", "definition");
        highConfidenceCard.flip();
        highConfidenceCard.recordConfidence(Confidence.HIGH);
        Deck deck = new Deck(List.of(lowConfidenceCard, highConfidenceCard));

        assertThat(deck.lowConfidenceCards())
                .containsOnly(lowConfidenceCard);
    }

    @Test
    void returnsUnknownConfidenceCards() {
        Card lowConfidenceCard = new Card("concept", "definition");
        lowConfidenceCard.flip();
        lowConfidenceCard.recordConfidence(Confidence.LOW);
        Card unknownConfidenceCard = new Card("concept", "definition");
        Deck deck = new Deck(List.of(lowConfidenceCard, unknownConfidenceCard));

        assertThat(deck.unknownConfidenceCards())
                .containsOnly(unknownConfidenceCard);
    }
}
