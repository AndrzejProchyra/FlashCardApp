package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudySessionConfidenceOrderTest {

    @Test
    @Disabled
    void deckWithCardsInRandomOrder_ReturnsCardsInReverseConfidenceOrder() {
        Card unknown = new Card("a", "a");
        Card low = new Card("b", "b", Confidence.LOW);
        Card medium = new Card("c", "c", Confidence.MEDIUM);
        Card high = new Card("d", "d", Confidence.HIGH);
        Deck deck = new Deck(List.of(medium, low, high, unknown));
        StudySession studySession = new StudySession(deck, 4);

        assertThat(studySession.nextCard().confidence())
                .isEqualTo(Confidence.UNKNOWN);
        assertThat(studySession.nextCard().confidence())
                .isEqualTo(Confidence.LOW);
        assertThat(studySession.nextCard().confidence())
                .isEqualTo(Confidence.MEDIUM);
        assertThat(studySession.nextCard().confidence())
                .isEqualTo(Confidence.HIGH);
    }
}
