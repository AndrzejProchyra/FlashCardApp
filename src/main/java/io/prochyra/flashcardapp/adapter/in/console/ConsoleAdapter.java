package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Card;
import io.prochyra.flashcardapp.domain.Deck;

import java.util.List;
import java.util.Scanner;

public class ConsoleAdapter {

    private final Scanner scanner;
    private final Deck deck;

    public ConsoleAdapter(Deck deck) {
        this.deck = deck;
        scanner = new Scanner(System.in);
    }

    public void start() {
        List<Card> unknownConfidenceCards = deck.unknownConfidenceCards();
        for (Card card : unknownConfidenceCards) {
            askAboutOneCard(card);
        }
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
