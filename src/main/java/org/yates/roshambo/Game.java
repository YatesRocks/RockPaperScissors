package org.yates.roshambo;

import org.yates.roshambo.player.ComputerPlayer;
import org.yates.roshambo.player.HumanPlayer;
import org.yates.utils.InputHandler;

import java.util.Objects;

public class Game {
    private Player playerA;
    private Player playerB;


    public Game() {}

    public void run() {
        InputHandler handler = InputHandler.builder()
                .prompt("Would you like to...\n1) Play against a computer\n2) Spectate\n(1/2): ")
                .errorMessage("Enter either the number 1 or number 2")
                .validator(in -> in.equals("1") || in.equals("2"))
                .build();
        switch (handler.query()) {
            case "1": playGame(); break;
            case "2": spectateGame(); break;
        }
    }

    private void spectateGame() {
        playerA = new ComputerPlayer(1);
        logic();
    }

    private void playGame() {
        playerA = HumanPlayer.getHumanPlayer();
        logic();
    }

    private void logic() {
        playerB = new ComputerPlayer(2);
        Move playerAMove = playerA.getMove();
        Move playerBMove = playerB.getMove();
        switch (playerAMove.whoWins(playerBMove)) {
            case WIN: System.out.println(playerA.getName() + " wins!!"); break;
            case LOSS: System.out.println(playerB.getName() + " wins!!"); break;
            case DRAW: System.out.println("It's a draw!!!"); break;
        }
        promptRetry();
    }

    private void promptRetry() {
        InputHandler handler = InputHandler.builder()
                .prompt("Would you like to try again? (Y)es or (N)o? ")
                .errorMessage("Enter a letter (Y)es or (N)o.")
                .validator(in -> in.equals("y") || in.equals("n"))
                .build();
        if (Objects.equals(handler.query(), "y"))
            run();
        System.out.println("Thanks for playing\n- Jordan");
    }
}
