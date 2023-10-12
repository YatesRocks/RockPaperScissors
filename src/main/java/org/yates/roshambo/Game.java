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
        if (playerA == null)
            playerA = HumanPlayer.getHumanPlayer();
        if (playerB == null)
            playerB = new ComputerPlayer(2);
        logic();
    }

    private void logic() {
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
