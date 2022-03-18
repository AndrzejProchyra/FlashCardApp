package io.prochyra.flashcardapp.domain;

public enum Confidence {
    UNKNOWN(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int order;

    Confidence(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }
}
