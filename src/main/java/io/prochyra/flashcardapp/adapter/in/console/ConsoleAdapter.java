package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;
import io.prochyra.flashcardapp.domain.StudySession;

import java.util.Scanner;

public class ConsoleAdapter {

    private final Scanner scanner;
    private final Deck deck;

    public ConsoleAdapter(Deck deck) {
        this.deck = deck;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("How many cards do you want to see? ");
        int numberOfCards = Integer.parseInt(scanner.nextLine());
        StudySession studySession = new StudySession(deck, numberOfCards);

        while (studySession.hasNextCard()) {
            askAboutOneCard(studySession.nextCard());
        }
        
        System.out.println("Stats for this deck:");
        System.out.println("High confidence: " + deck.highConfidenceCount());
        System.out.println("Medium confidence: " + deck.mediumConfidenceCount());
        System.out.println("Low confidence: " + deck.lowConfidenceCount());
    }

    private void askAboutOneCard(Card card) {
        System.out.println(card.content());
        scanner.nextLine();
        card.flip();
        System.out.println(card.content());
        System.out.println("Enter confidence level [L = LOW, M = MEDIUM, H = HIGH]: ");
        String answer = scanner.nextLine();
        card.recordConfidence(new ConsoleConfidence().from(answer));
        System.out.println("Confidence recorded. You're doing great!");
    }
}
