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
    void turnedCardHasDefinition() {
        Card card = new Card("Capital of France", "Paris");

        card.turn();

        assertThat(card.content())
                .isEqualTo("Paris");
    }

    @Test
    void turningCardTwiceThrowsException() {
        Card card = new Card("concept", "definition");

        card.turn();
        
        assertThatThrownBy(card::turn)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Card already turned");
    }
}
