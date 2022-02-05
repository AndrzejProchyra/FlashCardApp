package io.prochyra.flashcardapp;

public class Card {

  private final String concept;

  public Card(String concept) {
    this.concept = concept;
  }

  public String content() {
    return concept;
  }
}
