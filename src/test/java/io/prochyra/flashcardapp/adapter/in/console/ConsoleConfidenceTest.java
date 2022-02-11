package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Confidence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ConsoleConfidenceTest {

    @Test
    void givenUpperCaseLetterLReturnsLow() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("L");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.LOW);
    }

    @Test
    void givenLowerCaseLetterLReturnsLow() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("l");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.LOW);
    }

    @Test
    void givenUpperCaseLetterMReturnsMedium() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("M");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.MEDIUM);

    }

    @Test
    void givenUpperCaseLetterHReturnsHigh() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("H");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.HIGH);
    }

    @Test
    void givenLowerCaseFullWordLowReturnsLow() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("low");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.LOW);
    }

    @Test
    void givenMixedCaseFullWordLowReturnsLow() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("LoW");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.LOW);
    }

    @Test
    void givenPartialWordMedReturnsMedium() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        Confidence confidence = consoleConfidence.from("med");

        assertThat(confidence)
                .isEqualByComparingTo(Confidence.MEDIUM);

    }

    @Test
    void givenEmptyStringThrowsIllegalArgumentException() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        assertThatThrownBy(() -> consoleConfidence.from(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenInvalidChoiceThrowsIllegalArgumentException() {
        ConsoleConfidence consoleConfidence = new ConsoleConfidence();

        assertThatThrownBy(() -> consoleConfidence.from("x"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
