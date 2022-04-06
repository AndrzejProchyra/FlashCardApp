package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.application.CardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlashCardConfiguration {
    
    @Bean
    CardService cardService(CardRepository repository) {
        return new CardService(repository);
    }
}
