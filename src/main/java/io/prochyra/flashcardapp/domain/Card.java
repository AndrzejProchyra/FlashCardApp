package io.prochyra.flashcardapp.domain;

public class Card {

    private final String definition;
    private final String concept;
    private CardId id;
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

    public String concept() {
        return concept;
    }

    public String definition() {
        return definition;
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


    public CardId getId() {
        return id;
    }

    public void setId(CardId id) {
        this.id = id;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return id != null ? id.equals(card.id) : card.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", definition='" + definition + '\'' +
                ", concept='" + concept + '\'' +
                ", isFlipped=" + isFlipped +
                ", confidence=" + confidence +
                '}';
    }
}
