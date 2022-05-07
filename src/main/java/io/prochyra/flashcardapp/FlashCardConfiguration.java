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
        Deck deck = new Deck(List.of(
                new Card("first card concept", "doesn't matter")
        ));
        return new StudySession(deck, 1);
    }
}
