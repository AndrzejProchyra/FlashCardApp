package io.prochyra.flashcardapp.application;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.application.port.InMemoryCardRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddCardServiceTest {

  @Test
  void addCreatesNewCardInRepository() {
    CardRepository repository = new InMemoryCardRepository();
    AddCardService addCardService = new AddCardService(repository);

    addCardService.add("Concept 1", "Definition 1");

    assertThat(repository.findAll())
        .hasSize(1)
        .first()
        .extracting("concept", "definition")
        .contains("Concept 1", "Definition 1");

  }
}
