package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.application.port.CardRepository;
import io.prochyra.flashcardapp.application.port.InMemoryCardRepository;
import io.prochyra.flashcardapp.domain.Card;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardCreatorTest {

    @Test
    void modelContainsAllCardsFromRepositoryAsCardViews() {
        CardRepository repository = new InMemoryCardRepository();
        Card card1 = new Card("concept1", "defintion1");
        Card card2 = new Card("concept2", "defintion2");
        repository.save(card1);
        repository.save(card2);
        CardCreator cardCreator = new CardCreator(repository);

        Model model = new ConcurrentModel();
        cardCreator.homePage(model);

        List<CardView> cards = (List<CardView>) model.getAttribute("cards");
        assertThat(cards)
                .hasSize(2);
    }
}
