package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;

public class CardForm {
    
    private String concept;
    private String definition;

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getConcept() {
        return concept;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public Card toCard() {
        return new Card(concept, definition);
    }
}
