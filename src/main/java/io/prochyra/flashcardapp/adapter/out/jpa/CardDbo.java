package io.prochyra.flashcardapp.adapter.out.jpa;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Confidence;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class CardDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "definition")
    private String definition;

    @Column(name = "concept")
    private String concept;

    @Enumerated(EnumType.STRING)
    @Column(name = "confidence")
    private Confidence confidence;

    public Confidence getConfidence() {
        return confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Card asCard() {
        return new Card(concept, definition, confidence);
    }
}
