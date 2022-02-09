package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("concept", "definition");
    }

    @Test
    void initialDisplayOfCardHasConcept() {
        Card card = new Card("Capital of France", "Paris");

        assertThat(card.content())
                .isEqualTo("Capital of France");
    }

    @Test
    void flippedCardHasDefinition() {
        Card card = new Card("Capital of France", "Paris");

        card.flip();

        assertThat(card.content())
                .isEqualTo("Paris");
    }

    @Test
    void flippingCardTwiceThrowsException() {
        card.flip();

        assertThatThrownBy(card::flip)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Card already flipped");
    }

    @ParameterizedTest
    @CsvSource({"LOW", "MEDIUM", "HIGH"})
    void canRecordConfidenceForFlippedCard(Confidence confidence) {
        card.flip();

        card.recordConfidence(confidence);

        assertThat(card.confidence())
                .isEqualTo(confidence);
    }

    @Test
    void recordingConfidenceOnUnflippedCardThrowsException() {
        assertThatThrownBy(() -> card.recordConfidence(Confidence.LOW))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Card not flipped");
    }
}
