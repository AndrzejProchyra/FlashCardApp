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
        StudySession studySession = createStudySessionWithOneDummyCard();
        FlashCardController flashCardController = new FlashCardController(studySession);
        Model model = new ConcurrentModel();

        assertThat(flashCardController.flashCard(model))
                .isEqualTo("flashcard");
    }

    @Test
    void newSessionRedirectsToFlashcard() {
        FlashCardController flashCardController = new FlashCardController();

        String redirect = flashCardController.newSession();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void givenNewSessionWhenFlashcardThenFirstCardIsShown() {
        Deck deck = new Deck(List.of(
                new Card("first card concept", "doesn't matter")
        ));
        StudySession studySession = new StudySession(deck, 1);

        FlashCardController flashCardController = new FlashCardController(studySession);

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
        StudySession studySession = createStudySessionWithOneDummyCard();

        FlashCardController flashCardController = new FlashCardController(studySession);

        String redirect = flashCardController.flip();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @NotNull
    private StudySession createStudySessionWithOneDummyCard() {
        Deck deck = new Deck(List.of(
                new Card("doesn't matter", "doesn't matter")
        ));
        StudySession studySession = new StudySession(deck, 1);
        return studySession;
    }

    @Test
    void givenFlipFlashcardShowsDefinition() {
        Deck deck = new Deck(List.of(
                new Card("doesn't matter", "first card definition")
        ));
        StudySession studySession = new StudySession(deck, 1);

        FlashCardController flashCardController = new FlashCardController(studySession);
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
        FlashCardController flashCardController = new FlashCardController();

        String redirect = flashCardController.recordConfidence();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }
}
