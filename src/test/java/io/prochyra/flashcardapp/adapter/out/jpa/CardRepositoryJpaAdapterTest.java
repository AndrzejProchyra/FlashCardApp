package io.prochyra.flashcardapp.adapter.out.jpa;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Confidence;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@Tag("integration")
class CardRepositoryJpaAdapterTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withPassword("inmemory")
            .withUsername("inmemory");
    @Autowired
    CardJpaRepository cardJpaRepository;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Test
    void emptyRepositoryFindAllGetsEmptyList() {
        CardRepository cardRepositoryJpaAdapter = new CardRepositoryJpaAdapter(cardJpaRepository);

        List<Card> cards = cardRepositoryJpaAdapter.findAll();

        assertThat(cards)
                .isEmpty();
    }

    @Test
    void repositoryWithOneCardFindAllGetsCard() {
        CardDbo cardDbo = new CardDbo();
        cardDbo.setConcept("concept");
        cardDbo.setDefinition("definition");
        cardDbo.setConfidence(Confidence.UNKNOWN);
        cardJpaRepository.save(cardDbo);
        CardRepository cardRepository = new CardRepositoryJpaAdapter(cardJpaRepository);

        List<Card> cards = cardRepository.findAll();

        Card expectedCard = new Card("concept", "definition", Confidence.UNKNOWN);
        assertThat(cards)
                .containsExactly(expectedCard);
    }

    @Test
    void saveOneCardIsRetrievedUsingFindAll() {
        Card card = new Card("concept", "definition", Confidence.UNKNOWN);
        CardRepository cardRepository = new CardRepositoryJpaAdapter(cardJpaRepository);

        cardRepository.save(card);

        assertThat(cardRepository.findAll())
                .containsExactly(card);
    }

    @Test
    void saveCardReturnsSameCard() {
        Card card = new Card("concept", "definition", Confidence.UNKNOWN);
        CardRepository cardRepository = new CardRepositoryJpaAdapter(cardJpaRepository);

        Card savedCard = cardRepository.save(card);

        assertThat(savedCard)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(card);
    }

    @Test
    void saveUpdatesExistingCard() {
        Card card = new Card("concept", "definition", Confidence.UNKNOWN);
        CardRepository cardRepository = new CardRepositoryJpaAdapter(cardJpaRepository);

        Card savedCard = cardRepository.save(card);
        savedCard.flip();
        savedCard.recordConfidence(Confidence.HIGH);
        cardRepository.save(savedCard);

        List<Card> cards = cardRepository.findAll();
        assertThat(cards)
                .hasSize(1);
        assertThat(cards.get(0).confidence())
                .isEqualTo(Confidence.HIGH);
    }
}
