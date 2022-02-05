package io.prochyra.flashcardapp;

public class Card {

    private final String definition;
    private final String concept;
    private boolean isTurned;

    public Card(String concept, String definition) {
        this.concept = concept;
        this.definition = definition;
    }

    public String content() {
        return isTurned ? definition : concept;
    }

    public void turn() {
        requireCardNotTurned();
        isTurned = true;
    }

    private void requireCardNotTurned() {
        if (isTurned) {
            throw new IllegalStateException("Card already turned");
        }
    }
}
