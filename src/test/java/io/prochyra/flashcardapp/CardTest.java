package io.prochyra.flashcardapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {

  @Test
  void initialDisplayOfCardHasConcept() {
    Card card = new Card("Capital of France");

    assertThat(card.content())
        .isEqualTo("Capital of France");
  }
}
