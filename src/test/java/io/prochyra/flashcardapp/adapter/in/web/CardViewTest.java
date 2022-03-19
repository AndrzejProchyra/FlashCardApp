package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardViewTest {

    @Test
    void cardIsMappedToCardView() {
        Card card = new Card("Concept", "Definition");

        CardView cardView = CardView.from(card);

        assertThat(cardView.getConcept())
                .isEqualTo("Concept");
        assertThat(cardView.getDefinition())
                .isEqualTo("Definition");
    }
}
