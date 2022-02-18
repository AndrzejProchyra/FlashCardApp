package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudySessionTest {

    @Test
    void givenADeckWithOneCardAndWeCreateASessionWithOneCard_WhenWeAskForACard_ThenWeGetTheCard() {
        Card deckCard = new Card("concept", "definition");
        StudySession session = createWithDeckOf(deckCard);

        Card sessionCard = session.nextCard();

        assertThat(sessionCard)
                .isEqualTo(deckCard);
    }

    @Test
    void givenADeckWithTwoCardsAndWeCreateASessionWithTwoCards_WhenWeAskForTwoCards_ThenWeGetTheCards() {
        Card cardA = new Card("A", "A");
        Card cardB = new Card("B", "B");
        StudySession studySession = createWithDeckOf(cardA, cardB);

        assertThat(studySession.nextCard())
                .isEqualTo(cardA);
        assertThat(studySession.nextCard())
                .isEqualTo(cardB);
    }

    private StudySession createWithDeckOf(Card... cards) {
        Deck deck = new Deck(List.of(cards));
        return new StudySession(deck, cards.length);
    }
}
