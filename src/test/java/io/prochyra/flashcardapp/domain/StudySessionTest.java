package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudySessionTest {

    private static final Card ANY_CARD = new Card("x", "x");
    private static final Card CARD_A = new Card("A", "A");
    private static final Card CARD_B = new Card("B", "B");

    @Test
    void sessionOfOneCardFromDeckOfOneCard_AskForCard_GetCard() {
        StudySession session = createWithDeckOf(ANY_CARD);

        Card sessionCard = session.nextCard();

        assertThat(sessionCard)
                .isEqualTo(ANY_CARD);
        assertThat(session.hasNextCard())
                .isFalse();
    }

    @Test
    void sessionOfManyCardsFromDeckOfManyCards_AskForManyCards_GetCards() {
        StudySession studySession = createWithDeckOf(CARD_A, CARD_B);

        assertThat(studySession.nextCard())
                .isEqualTo(CARD_A);
        assertThat(studySession.nextCard())
                .isEqualTo(CARD_B);
    }

    @Test
    void sessionWithOneCard_askIfHasNextCard_ItDoes() {
        StudySession studySession = createWithDeckOf(ANY_CARD);

        assertThat(studySession.hasNextCard())
                .isTrue();
    }

    @Test
    void sessionOfOneCardFromDeckWithManyCards_AskingForSecondCardReturnsFalse() {
        Deck deck = new Deck(List.of(CARD_A, CARD_B));
        StudySession session = new StudySession(deck, 1);

        session.nextCard();

        assertThat(session.hasNextCard())
                .isFalse();
    }

    @Test
    void sessionOfZeroCardsThrowsException() {
        Deck deck = new Deck(List.of(ANY_CARD));

        assertThatThrownBy(() -> new StudySession(deck, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Study session must have at least one card.");
    }

    @Test
    void sessionWithMoreCardsThanDeckHasThrowsException() {
        Deck oneCardDeck = new Deck(List.of(ANY_CARD));

        assertThatThrownBy(() -> new StudySession(oneCardDeck, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Can't create session of 2 cards from deck with only 1.");
    }

    @Test
    void sessionWithOneCardAfterNextCardThenCurrentCardReturnsCard() {
        StudySession session = createWithDeckOf(CARD_A);
        session.nextCard();
        
        assertThat(session.currentCard())
                .isEqualTo(CARD_A);
    }

    private StudySession createWithDeckOf(Card... cards) {
        Deck deck = new Deck(List.of(cards));
        return new StudySession(deck, cards.length);
    }
}
