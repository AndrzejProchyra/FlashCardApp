package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.application.CardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlashCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashCardApplication.class, args);
    }

    @Bean
    CardService cardService(CardRepository repository) {
        return new CardService(repository);
    }
}
