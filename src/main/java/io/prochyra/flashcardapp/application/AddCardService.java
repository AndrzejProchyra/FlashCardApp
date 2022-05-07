package io.prochyra.flashcardapp.application;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;

public class AddCardService {
    private final CardRepository repository;

    public AddCardService(CardRepository repository) {
        this.repository = repository;
    }

    public void add(String concept, String definition) {
        repository.save(new Card(concept, definition));
    }
}
