package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.application.CardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;
import io.prochyra.flashcardapp.domain.StudySession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FlashCardConfiguration {

    @Bean
    CardService cardService(CardRepository repository) {
        return new CardService(repository);
    }

    @Bean
    StudySession studySession() {
        List<Card> cards = List.of(
                new Card("First concept", "First Definition"),
                new Card("Second concept", "Second Definition")
        );
        return new StudySession(new Deck(cards), 2);
    }
}
