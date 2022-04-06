package io.prochyra.flashcardapp.adapter.out.jpa;

import io.prochyra.flashcardapp.domain.Confidence;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Import(CardRepositoryJpaAdapter.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Testcontainers
@Tag("integration")
class CardDboTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
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
    void storedCardDboIsRetrievedFromJpaRepositoryWithIdAssigned() {
        CardDbo cardDbo = new CardDbo();
        cardDbo.setConcept("concept");
        cardDbo.setDefinition("definition");
        cardDbo.setConfidence(Confidence.UNKNOWN);

        cardJpaRepository.save(cardDbo);

        assertThat(cardJpaRepository.count())
                .isEqualTo(1L);

        CardDbo foundCardDbo = cardJpaRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();
        assertThat(foundCardDbo.getId())
                .isNotNull();
        assertThat(foundCardDbo)
                .extracting("concept", "definition", "confidence")
                .containsExactly("concept", "definition", Confidence.UNKNOWN);
    }
}
