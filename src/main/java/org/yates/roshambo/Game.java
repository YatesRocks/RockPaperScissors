package org.yates.roshambo;

import org.yates.roshambo.player.ComputerPlayer;
import org.yates.roshambo.player.HumanPlayer;
import org.yates.utils.InputHandler;

import java.util.InputMismatchException;
import java.util.Objects;

public class Game {

    private Player playerA;
    private Player playerB;


    public Game() {
        InputHandler handler = InputHandler.builder()
                .prompt("Play with a 1) friend or 2) computer? (1/2): ")
                .errorMessage("Please, enter only the digit '1' or '2'")
                .validator(in -> in.equals("1") || in.equals("2"))
                .build();
        switch (handler.query()) {
            case "1": initBothHuman(); break;
            case "2": initSinglePlayer(); break;
            default: throw new InputMismatchException("Illegal argument passed");
        }
    }

    private void initSinglePlayer() {
        playerA = HumanPlayer.getHumanPlayer();
        playerB = new ComputerPlayer(1);
    }

    private void initBothHuman() {
        playerA = HumanPlayer.getHumanPlayer();
        playerB = HumanPlayer.getHumanPlayer();
    }

    public void run() {
        Move playerAMove = playerA.getMove();
        Move playerBMove = playerB.getMove();
        switch (playerAMove.whoWins(playerBMove)) {
            case WIN: System.out.println(playerA.getName() + " wins with " + playerAMove + "!!"); break;
            case LOSS: System.out.println(playerB.getName() + " wins with " + playerBMove + "!!"); break;
            case DRAW: System.out.println("It's a draw!!!"); break;
        }
        promptRetry();
    }

    private void promptRetry() {
        InputHandler handler = InputHandler.builder()
                .prompt("Would you like to try again? (Y)es or (N)o: ")
                .errorMessage("Enter a letter. (Y/N)")
                .validator(in -> in.equals("y") || in.equals("n"))
                .build();
        if (Objects.equals(handler.query(), "y"))
            run();
    }
}
