package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Confidence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardFormTest {

    @Test
    void formIsMappedToCard() {
        CardForm cardForm = new CardForm();
        cardForm.setConcept("Concept");
        cardForm.setDefinition("Definition");

        Card card = cardForm.toCard();

        assertThat(card.concept())
                .isEqualTo("Concept");
        assertThat(card.definition())
                .isEqualTo("Definition");
        assertThat(card.confidence())
                .isEqualTo(Confidence.UNKNOWN);
    }
}
