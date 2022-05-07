package io.prochyra.flashcardapp.adapter.in.web;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;
import io.prochyra.flashcardapp.domain.StudySession;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FlashCardControllerTest {

    @Test
    void flashCardReturnsViewName() {
        FlashCardController flashCardController = startControllerWithDummySession();

        assertThat(flashCardController.flashCard(new ConcurrentModel()))
                .isEqualTo("flashcard");
    }

    @Test
    void newFlashCardControllerStudySessionHasNotStarted() {
        StudySession studySession = createStudySessionWithOneDummyCard();
        new FlashCardController(studySession);

        assertThat(studySession.currentCard())
                .isNull();
    }

    @Test
    void newSessionRedirectsToFlashcard() {
        FlashCardController flashCardController = new FlashCardController(createStudySessionWithOneDummyCard());

        String redirect = flashCardController.newSession();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void givenNewSessionWhenFlashcardThenFirstCardIsShown() {
        FlashCardController flashCardController = startControllerWhereSessionHasCard("first card concept", "doesn't matter");

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String concept = (String) model.getAttribute("content");
        Boolean showFlip = (Boolean) model.getAttribute("showFlip");
        assertThat(concept)
                .isEqualTo("first card concept");
        assertThat(showFlip)
                .isTrue();
    }

    @Test
    void flipRedirectsToFlashcard() {
        FlashCardController flashCardController = startControllerWithDummySession();

        String redirect = flashCardController.flip();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void givenFlipFlashcardShowsDefinition() {
        FlashCardController flashCardController = startControllerWhereSessionHasCard("doesn't matter", "first card definition");
        flashCardController.flip();

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String definition = (String) model.getAttribute("content");
        Boolean showFlip = (Boolean) model.getAttribute("showFlip");
        assertThat(definition)
                .isEqualTo("first card definition");
        assertThat(showFlip)
                .isFalse();
    }

    @Test
    void recordConfidenceRedirectsToFlashcard() {
        StudySession studySession = createStudySessionWithOneDummyCard();
        FlashCardController flashCardController = new FlashCardController(studySession);

        String redirect = flashCardController.recordConfidence();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @NotNull
    private StudySession createStudySessionWithOneDummyCard() {
        Deck deck = new Deck(List.of(
                new Card("doesn't matter", "doesn't matter")
        ));
        return new StudySession(deck, 1);
    }

    @NotNull
    private FlashCardController startControllerWithDummySession() {
        StudySession studySession = createStudySessionWithOneDummyCard();
        FlashCardController flashCardController = new FlashCardController(studySession);
        flashCardController.newSession();
        return flashCardController;
    }

    @NotNull
    private FlashCardController startControllerWhereSessionHasCard(String concept, String definition) {
        Deck deck = new Deck(List.of(
                new Card(concept, definition)
        ));
        StudySession studySession = new StudySession(deck, 1);
        FlashCardController flashCardController = new FlashCardController(studySession);
        flashCardController.newSession();
        return flashCardController;
    }
}
