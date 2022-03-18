package io.prochyra.flashcardapp.application.port;

import io.prochyra.flashcardapp.domain.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InMemoryCardRepositoryTest {

    @Test
    public void newRepositoryHasNoCards() throws Exception {
        InMemoryCardRepository cardRepository = new InMemoryCardRepository();

        assertThat(cardRepository.findAll())
                .isEmpty();
    }

    @Test
    public void savesOneCard() throws Exception {
        InMemoryCardRepository cardRepository = new InMemoryCardRepository();
        Card card = new Card("concept1", "definition1");

        cardRepository.save(card);

        assertThat(cardRepository.findAll())
                .containsExactly(card);
    }

    @Test
    public void savesTwoCards() throws Exception {
        fail("saves two cards");
    }

}
