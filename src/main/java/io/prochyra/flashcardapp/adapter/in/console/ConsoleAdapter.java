package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Card;

import java.util.Scanner;

public class ConsoleAdapter {

    private final Card card;
    private final Scanner scanner;

    public ConsoleAdapter(Card card) {
        this.card = card;
        scanner = new Scanner(System.in);
    }

    public void start() {
        askAboutOneCard(card);
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
