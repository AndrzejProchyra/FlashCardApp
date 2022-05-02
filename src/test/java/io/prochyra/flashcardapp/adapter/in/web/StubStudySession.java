package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;
import io.prochyra.flashcardapp.domain.StudySession;

public class StubStudySession extends StudySession {

  public StubStudySession() {
    super(null, 1);
  }

  public StubStudySession(Deck deck, int cardCount) {
    super(deck, cardCount);
  }

  @Override
  public Card nextCard() {
    return new Card("First concept", "First Definition");
  }
}
