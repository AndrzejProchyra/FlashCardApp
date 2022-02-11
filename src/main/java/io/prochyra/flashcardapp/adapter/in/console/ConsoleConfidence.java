package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Confidence;

public class ConsoleConfidence {

    public Confidence from(String choice) {
        requireNotEmpty(choice);

        String lowerCaseFirstLetterOfChoice = choice.substring(0, 1).toLowerCase();
        return switch (lowerCaseFirstLetterOfChoice) {
            case "l" -> Confidence.LOW;
            case "m" -> Confidence.MEDIUM;
            case "h" -> Confidence.HIGH;
            default -> throw new IllegalArgumentException();
        };
    }

    private void requireNotEmpty(String choice) {
        if (choice.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
