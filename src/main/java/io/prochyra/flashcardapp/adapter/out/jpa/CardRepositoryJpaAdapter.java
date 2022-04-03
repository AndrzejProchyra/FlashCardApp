package io.prochyra.flashcardapp.adapter.out.jpa;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.domain.Card;

import java.util.List;

public class CardRepositoryJpaAdapter implements CardRepository {

    private final CardJpaRepository cardJpaRepository;

    public CardRepositoryJpaAdapter(CardJpaRepository cardJpaRepository) {
        this.cardJpaRepository = cardJpaRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardJpaRepository.findAll()
                .stream()
                .map(CardDbo::asCard)
                .toList();
    }

    @Override
    public Card save(Card card) {
        CardDbo cardDbo = cardJpaRepository.save(CardDbo.from(card));
        return cardDbo.asCard();
    }
}
