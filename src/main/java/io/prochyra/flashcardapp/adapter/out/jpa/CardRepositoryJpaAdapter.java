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
                .map(this::asCard)
                .toList();
    }

    private Card asCard(CardDbo cardDbo) {
        Card card = new Card(cardDbo.getConcept(), cardDbo.getDefinition(), cardDbo.getConfidence());
        return card;
    }

    @Override
    public Card save(Card card) {
        return null;
    }
}
