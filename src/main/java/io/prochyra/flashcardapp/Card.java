package io.prochyra.flashcardapp;

public class Card {

  private final String definition;
  private String concept;

  public Card(String concept, String definition) {
    this.concept = concept;
    this.definition = definition;
  }

  public String content() {
    return concept;
  }

  public void turn() {
    concept = definition;
  }
}
