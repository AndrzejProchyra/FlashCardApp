package io.prochyra.flashcardapp;

import io.prochyra.flashcardapp.application.CardService;
import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlashCardAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashCardAppApplication.class, args);
    }

//    @Bean
//    CardRepository cardRepository() {
//        return new InMemoryCardRepository();
//    }

    @Bean
    CardService cardService(CardRepository repository) {
        return new CardService(repository);
    }

    @Bean
    ApplicationRunner dataLoader(CardRepository cardRepository) {
        return args -> {
            cardRepository.save(new Card("concept1", "definition1"));
            cardRepository.save(new Card("concept2", "definition2"));
            cardRepository.save(new Card("concept3", "definition3"));
            cardRepository.save(new Card("concept4", "definition4"));
        };
    }
}
