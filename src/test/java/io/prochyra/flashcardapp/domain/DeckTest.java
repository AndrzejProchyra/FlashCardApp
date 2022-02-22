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

    @Test
    void givesACountOfHighConfidenceCards() {
        Card unknownConfidenceCard1 = new Card("concept", "definition");
        Card unknownConfidenceCard2 = new Card("concept", "definition");
        Card highConfidenceCard = new Card("concept", "definition");
        highConfidenceCard.flip();
        highConfidenceCard.recordConfidence(Confidence.HIGH);
        Deck deck = new Deck(List.of(unknownConfidenceCard1, unknownConfidenceCard2, highConfidenceCard));

        assertThat(deck.highConfidenceCount()).isOne();
    }

    @Test
    void givesTotalCountOfCards() {
        List<Card> threeCards = List.of(
                new Card("a", "a"), 
                new Card("b", "b"), 
                new Card("c", "c")
        );
        
        Deck deck = new Deck(threeCards);
        
        assertThat(deck.totalCardCount())
                .isEqualTo(3);
    }
}
