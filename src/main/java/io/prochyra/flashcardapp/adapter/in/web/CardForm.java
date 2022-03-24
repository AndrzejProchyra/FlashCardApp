package io.prochyra.flashcardapp.adapter.in.web;

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

}
