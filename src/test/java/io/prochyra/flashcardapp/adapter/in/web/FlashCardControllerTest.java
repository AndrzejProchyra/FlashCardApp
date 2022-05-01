package io.prochyra.flashcardapp.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

class FlashCardControllerTest {

    @Test
    void flashCardReturnsViewName() {
        FlashCardController flashCardController = new FlashCardController();
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
        FlashCardController flashCardController = new FlashCardController();

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String concept = (String) model.getAttribute("content");
        boolean showFlip = (boolean) model.getAttribute("showFlip");
        assertThat(concept)
                .isEqualTo("first concept");
        assertThat(showFlip)
                .isTrue();
    }

    @Test
    void flipRedirectsToFlashcard() {
        FlashCardController flashCardController = new FlashCardController();

        String redirect = flashCardController.flip();

        assertThat(redirect)
                .isEqualTo("redirect:/flashcard");
    }

    @Test
    void givenFlipFlashcardShowsDefinition() {
        FlashCardController flashCardController = new FlashCardController();
        flashCardController.flip();

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        String definition = (String) model.getAttribute("content");
        boolean showFlip = (boolean) model.getAttribute("showFlip");
        assertThat(definition)
                .isEqualTo("first definition");
        assertThat(showFlip)
                .isFalse();
    }
}
