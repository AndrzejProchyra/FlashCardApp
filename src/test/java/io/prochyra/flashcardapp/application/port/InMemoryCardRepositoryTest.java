package io.prochyra.flashcardapp.application.port;

import io.prochyra.flashcardapp.domain.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryCardRepositoryTest {

    @Test
    void newRepositoryHasNoCards() {
        CardRepository cardRepository = new InMemoryCardRepository();

        assertThat(cardRepository.findAll())
                .isEmpty();
    }

    @Test
    void savesOneCard() {
        CardRepository cardRepository = new InMemoryCardRepository();
        Card card = new Card("concept1", "definition1");

        cardRepository.save(card);

        assertThat(cardRepository.findAll())
                .containsExactly(card);
    }

    @Test
    void savesTwoCards() {
        CardRepository cardRepository = new InMemoryCardRepository();
        Card card1 = new Card("concept1", "definition1");
        Card card2 = new Card("concept2", "definition2");

        cardRepository.save(card1);
        cardRepository.save(card2);

        assertThat(cardRepository.findAll())
                .containsExactly(card1, card2);
    }

    @Test
    void returnsSavedCard() {
        CardRepository cardRepository = new InMemoryCardRepository();
        Card card = new Card("concept1", "definition1");

        assertThat(cardRepository.save(card))
                .isEqualTo(card);

    }
}
