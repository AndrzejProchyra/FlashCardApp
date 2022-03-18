package io.prochyra.flashcardapp.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConfidenceOrderTest {

    @Test
    void confidenceLevelsHaveSortOrder() {
        List<Confidence> confidences = new ArrayList<>(List.of(Confidence.values()));

        confidences.sort(Comparator.comparing(Confidence::order));

        assertThat(confidences)
                .containsExactly(Confidence.UNKNOWN, Confidence.LOW, Confidence.MEDIUM, Confidence.HIGH);
    }
}
