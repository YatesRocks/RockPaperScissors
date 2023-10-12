package org.yates.roshambo.player;

import org.yates.roshambo.Move;
import org.yates.roshambo.Player;
import org.yates.utils.InputHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class HumanPlayer extends Player {

    private final BufferedReader reader;

    private HumanPlayer(String name) {
        super(name);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static HumanPlayer getHumanPlayer() {
        InputHandler handler = InputHandler.builder()
                .prompt("Please enter a name: ")
                .validator(in -> true)
                .build();
        String name = handler.query();
        System.out.println("Nice to meet you, " + name);
        return new HumanPlayer(name);
    }

    private HumanPlayer(String name, BufferedReader reader) {
        super(name);
        this.reader = reader;
    }

    public static HumanPlayer getHumanPlayer(BufferedReader reader) {
        InputHandler handler = InputHandler.builder()
                .prompt("Please enter a name: ")
                .setReader(reader)
                .validator(in -> true)
                .build();
        String name = handler.query();
        System.out.println("Nice to meet you, " + name);
        return new HumanPlayer(name, reader);
    }

    public Move getMove() {
        System.out.println(this.name + " it's your turn!");
        InputHandler handler = InputHandler.builder()
                .prompt("Rock, Paper, or Scissors? (R/P/S): ")
                .validator(in -> in.equals("r") || in.equals("p") || in.equals("s"))
                .setReader(reader)
                .errorMessage("Please enter a valid letter (R/P/S)")
                .build();
        return switch (handler.query()) {
            case "r" -> Move.ROCK;
            case "p"-> Move.PAPER;
            case "s" -> Move.SCISSORS;
            default -> throw new InputMismatchException("Invalid move");
        };
    }

}
