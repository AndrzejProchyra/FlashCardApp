package io.prochyra.flashcardapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

}
