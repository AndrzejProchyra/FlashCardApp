package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;

public class CardView {

    private final String concept;
    private final String definition;

    public CardView(String concept, String definition) {
        this.concept = concept;
        this.definition = definition;
    }

    public static CardView from(Card card) {
        return new CardView(card.concept(), card.definition());
    }

    public String getConcept() {
        return concept;
    }

    public String getDefinition() {
        return definition;
    }
}
