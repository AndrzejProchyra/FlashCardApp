package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudySessionTest {

    // Session with one card

    @Test
    void givenADeckWithOneCardAndWeCreateASessionWithOneCard_WhenWeAskForACard_ThenWeGetTheCard() {
        Card deckCard = new Card("concept", "definition");
        Deck deck = new Deck(List.of(deckCard));
        StudySession session = new StudySession(deck, 1);

        Card sessionCard = session.nextCard();
        
        assertThat(sessionCard)
                .isEqualTo(deckCard);
    }
}
