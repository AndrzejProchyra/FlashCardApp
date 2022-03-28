package io.prochyra.flashcardapp.adapter.out.jpa;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;

import java.util.Collections;
import java.util.List;

public class CardRepositoryJpaAdapter implements CardRepository {

    @Override
    public List<Card> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Card save(Card card) {
        return null;
    }
}
