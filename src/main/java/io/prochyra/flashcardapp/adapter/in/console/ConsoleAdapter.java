package io.prochyra.flashcardapp.adapter.in.console;

import io.prochyra.flashcardapp.domain.Card;

import java.util.Scanner;

public class ConsoleAdapter {

    private final Card card;

    public ConsoleAdapter(Card card) {
        this.card = card;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
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
