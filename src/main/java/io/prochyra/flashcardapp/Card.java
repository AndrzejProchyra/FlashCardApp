package io.prochyra.flashcardapp;

public class Card {

  private String concept;

  public Card(String concept, String definition) {
    this.concept = concept;
  }

  public String content() {
    return concept;
  }

  public void turn() {
    concept = "Paris";
  }
}
