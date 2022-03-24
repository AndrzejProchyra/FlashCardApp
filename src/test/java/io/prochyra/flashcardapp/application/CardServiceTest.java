package io.prochyra.flashcardapp.application;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.application.port.InMemoryCardRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardServiceTest {

  @Test
  void addCreatesNewCardInRepository() {
    CardRepository repository = new InMemoryCardRepository();
    CardService cardService = new CardService(repository);

    cardService.add("Concept 1", "Definition 1");

    assertThat(repository.findAll())
        .hasSize(1)
        .first()
        .extracting("concept", "definition")
        .contains("Concept 1", "Definition 1");

  }
}
