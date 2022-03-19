package io.prochyra.flashcardapp.application.port;

import io.prochyra.flashcardapp.domain.Card;

import java.util.List;

public interface CardRepository {
    List<Card> findAll();

    Card save(Card card);
}
