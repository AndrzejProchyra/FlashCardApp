package io.prochyra.flashcardapp.domain;

public class Card {

    private final String definition;
    private final String concept;
    private boolean isFlipped;
    private Confidence confidence;

    public Card(String concept, String definition) {
        this(concept, definition, Confidence.UNKNOWN);
    }

    public Card(String concept, String definition, Confidence confidence) {
        this.concept = concept;
        this.definition = definition;
        this.confidence = confidence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (isFlipped != card.isFlipped) return false;
        if (!definition.equals(card.definition)) return false;
        if (!concept.equals(card.concept)) return false;
        return confidence == card.confidence;
    }

    @Override
    public int hashCode() {
        int result = definition.hashCode();
        result = 31 * result + concept.hashCode();
        result = 31 * result + (isFlipped ? 1 : 0);
        result = 31 * result + confidence.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "definition='" + definition + '\'' +
                ", concept='" + concept + '\'' +
                ", isFlipped=" + isFlipped +
                ", confidence=" + confidence +
                '}';
    }
}
