package io.prochyra.flashcardapp.domain;

public class Card {

    private final String definition;
    private final String concept;
    private boolean isFlipped;
    private Confidence confidence;

    public Card(String concept, String definition) {
        this.concept = concept;
        this.definition = definition;
        confidence = Confidence.UNKNOWN;
    }

    public String content() {
        return isFlipped ? definition : concept;
    }

    public void flip() {
        requireCardNotFlipped();
        isFlipped = true;
    }

    public void recordConfidence(Confidence confidence) {
        requireCardFlipped();
        this.confidence = confidence;
    }

    public Confidence confidence() {
        return confidence;
    }

    private void requireCardNotFlipped() {
        if (isFlipped) {
            throw new IllegalStateException("Card already flipped");
        }
    }

    private void requireCardFlipped() {
        if (!isFlipped) {
            throw new IllegalStateException("Card not flipped");
        }
    }
}
