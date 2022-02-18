package io.prochyra.flashcardapp.domain;

public class StudySession {
    public StudySession(Deck deck, int cardCount) {

    }

    public Card nextCard() {
        return new Card("concept", "definition");
    }
}
