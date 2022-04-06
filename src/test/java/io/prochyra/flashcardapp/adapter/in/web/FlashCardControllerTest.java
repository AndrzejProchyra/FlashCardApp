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
    void flashCardAddsConceptToModel() {
        FlashCardController flashCardController = new FlashCardController();

        Model model = new ConcurrentModel();
        flashCardController.flashCard(model);

        assertThat(model.containsAttribute("concept"))
                .isTrue();
    }
}
