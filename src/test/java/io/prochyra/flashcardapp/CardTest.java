package io.prochyra.flashcardapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardTest {

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
        Card card = new Card("concept", "definition");

        card.flip();
        
        assertThatThrownBy(card::flip)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Card already flipped");
    }
}
