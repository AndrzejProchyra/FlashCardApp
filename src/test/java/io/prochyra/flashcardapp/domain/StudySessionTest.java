package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudySessionTest {
    
    @Test
    void givenADeckWithOneCardAndWeCreateASessionWithOneCard_WhenWeAskForACard_ThenWeGetTheCard() {
        Card deckCard = new Card("concept", "definition");
        Deck deck = new Deck(List.of(deckCard));
        StudySession session = new StudySession(deck, 1);

        Card sessionCard = session.nextCard();
        
        assertThat(sessionCard)
                .isEqualTo(deckCard);
    }

    @Test
    void givenADeckWithTwoCardsAndWeCreateASessionWithTwoCards_WhenWeAskForTwoCards_ThenWeGetTheCards() {
        Card cardA = new Card("A", "A");
        Card cardB = new Card("B", "B");
        Deck deck = new Deck(List.of(cardA, cardB));
        StudySession studySession = new StudySession(deck, 2);

        assertThat(studySession.nextCard())
                .isEqualTo(cardA);
        assertThat(studySession.nextCard())
                .isEqualTo(cardB);
    }
}
