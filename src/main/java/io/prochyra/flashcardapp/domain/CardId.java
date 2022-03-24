package io.prochyra.flashcardapp.domain;

public record CardId(long id) {
    public static CardId of(long id) {
        return new CardId(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "=" + id;
    }
}
