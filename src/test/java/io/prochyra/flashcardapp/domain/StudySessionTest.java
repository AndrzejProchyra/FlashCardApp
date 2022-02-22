package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudySessionTest {

    @Test
    void givenADeckWithOneCardAndWeCreateASessionWithOneCard_WhenWeAskForACard_ThenWeGetTheCard() {
        Card deckCard = new Card("concept", "definition");
        StudySession session = createWithDeckOf(deckCard);

        Card sessionCard = session.nextCard();

        assertThat(sessionCard)
                .isEqualTo(deckCard);
        assertThat(session.hasNextCard())
                .isFalse();
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

    @Test
    void givenASessionWithOneCard_WhenWeAskIfHasNextCard_ThenItDoes() {
        Card card = new Card("x", "x");
        StudySession studySession = createWithDeckOf(card);

        assertThat(studySession.hasNextCard())
                .isTrue();
    }

    @Test
    void givenSessionOfOneCardCreatedFromDeckWithTwoCards_HasNextCardReturnsFalse() {
        Card cardA = new Card("a", "a");
        Card cardB = new Card("b", "b");
        Deck deck = new Deck(List.of(cardA, cardB));
        StudySession session = new StudySession(deck, 1);

        session.nextCard();

        assertThat(session.hasNextCard())
                .isFalse();
    }

    @Test
    void sessionOfZeroCardCountThrowsException() {
        Deck deck = new Deck(List.of());

        assertThatThrownBy(() -> new StudySession(deck, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private StudySession createWithDeckOf(Card... cards) {
        Deck deck = new Deck(List.of(cards));
        return new StudySession(deck, cards.length);
    }
}
