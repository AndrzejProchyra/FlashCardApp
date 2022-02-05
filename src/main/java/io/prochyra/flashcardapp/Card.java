package io.prochyra.flashcardapp;

public class Card {

  private final String definition;
  private String concept;
  private boolean isTurned;

  public Card(String concept, String definition) {
    this.concept = concept;
    this.definition = definition;
  }

  public String content() {
    if (isTurned) {
      return definition;
    }
    return concept;
  }

  public void turn() {
    isTurned = true;
  }
}
