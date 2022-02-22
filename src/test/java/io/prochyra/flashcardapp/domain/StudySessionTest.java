package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudySessionTest {

    @Test
    void sessionOfOneCardFromDeckOfOneCard_AskForCard_GetCard() {
        Card deckCard = new Card("concept", "definition");
        StudySession session = createWithDeckOf(deckCard);

        Card sessionCard = session.nextCard();

        assertThat(sessionCard)
                .isEqualTo(deckCard);
        assertThat(session.hasNextCard())
                .isFalse();
    }

    @Test
    void sessionOfManyCardsFromDeckOfManyCards_AskForManyCards_GetCards() {
        Card cardA = new Card("A", "A");
        Card cardB = new Card("B", "B");
        StudySession studySession = createWithDeckOf(cardA, cardB);

        assertThat(studySession.nextCard())
                .isEqualTo(cardA);
        assertThat(studySession.nextCard())
                .isEqualTo(cardB);
    }

    @Test
    void sessionWithOneCard_askIfHasNextCard_ItDoes() {
        Card card = new Card("x", "x");
        StudySession studySession = createWithDeckOf(card);

        assertThat(studySession.hasNextCard())
                .isTrue();
    }

    @Test
    void sessionOfOneCardFromDeckWithManyCards_AskingForSecondCardReturnsFalse() {
        Card cardA = new Card("a", "a");
        Card cardB = new Card("b", "b");
        Deck deck = new Deck(List.of(cardA, cardB));
        StudySession session = new StudySession(deck, 1);

        session.nextCard();

        assertThat(session.hasNextCard())
                .isFalse();
    }

    @Test
    void sessionOfZeroCardsThrowsException() {
        Deck deck = new Deck(List.of());

        assertThatThrownBy(() -> new StudySession(deck, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Study session must have at least one card.");
    }

    @Test
    void sessionWithMoreCardsThanDeckHasThrowsException() {
        Deck deck = new Deck(List.of(new Card("", "")));

        assertThatThrownBy(() -> new StudySession(deck, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Can't create session of 2 cards from deck with only 1.");
    }

    private StudySession createWithDeckOf(Card... cards) {
        Deck deck = new Deck(List.of(cards));
        return new StudySession(deck, cards.length);
    }
}
