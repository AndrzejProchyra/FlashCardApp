package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;
import io.prochyra.flashcardapp.domain.StudySession;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FlashCardControllerTest {

    StudySession DUMMY_STUDY_SESSION = null;

    @Test
    void flashCardReturnsViewName() {
        FlashCardController flashCardController = new FlashCardController(DUMMY_STUDY_SESSION);

        Model model = new ConcurrentModel();

        assertThat(flashCardController.flashCard(model))
                .isEqualTo("flashcard");
    }

    @Test
    void newSessionRedirectsToFlashcard() {
        StudySession studySession = new StudySession(new Deck(List.of(new Card("First concept", "First Definition"))), 1);
        FlashCardController flashCardController = new FlashCardController(studySession);

        
        String redirect = flashCardController.newSession();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void givenNewSessionWhenFlashcardThenFirstCardIsShown() {
        FlashCardController flashCardController = new FlashCardController(DUMMY_STUDY_SESSION);

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String concept = (String) model.getAttribute("content");
        Boolean showFlip = (Boolean) model.getAttribute("showFlip");
        assertThat(concept)
                .isEqualTo("first concept");
        assertThat(showFlip)
                .isTrue();
    }

    @Test
    void flipRedirectsToFlashcard() {
        FlashCardController flashCardController = new FlashCardController(DUMMY_STUDY_SESSION);

        String redirect = flashCardController.flip();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void givenFlipFlashcardShowsDefinition() {
        FlashCardController flashCardController = new FlashCardController(DUMMY_STUDY_SESSION);

        flashCardController.flip();

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String definition = (String) model.getAttribute("content");
        Boolean showFlip = (Boolean) model.getAttribute("showFlip");
        assertThat(definition)
                .isEqualTo("first definition");
        assertThat(showFlip)
                .isFalse();
    }

    @Test
    void recordConfidenceRedirectsToFlashcard() {
        List<Card> cards = List.of(
                new Card("Concept 1", "Definition 1"),
                new Card("Concept 2", "Definition 2")
        );
        Deck deck = new Deck(cards);
        StudySession studySession = new StudySession(deck, 2);
        FlashCardController flashCardController = new FlashCardController(studySession);


        String redirect = flashCardController.recordConfidence();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void recordingConfidenceShowsNextCard() {
        List<Card> cards = List.of(
                new Card("Concept 1", "Definition 1"),
                new Card("Concept 2", "Definition 2")
        );
        Deck deck = new Deck(cards);
        StudySession studySession = new StudySession(deck, 2);
        FlashCardController flashCardController = new FlashCardController(studySession);
        flashCardController.newSession();

        flashCardController.recordConfidence();

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String concept = (String) model.getAttribute("content");
        assertThat(concept)
                .isEqualTo("Concept 2");
    }
}
