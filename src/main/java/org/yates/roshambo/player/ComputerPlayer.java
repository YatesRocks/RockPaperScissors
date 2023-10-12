package org.yates.roshambo.player;

import org.yates.roshambo.Move;
import org.yates.roshambo.Player;

import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player {
    public ComputerPlayer(int number) {
        super("Computer" + number);
    }

    public Move getMove() {
        switch (ThreadLocalRandom.current().nextInt(1, 4)) {
            case 1: return Move.ROCK;
            case 2: return Move.PAPER;
            case 3: return Move.SCISSORS;
        }
        throw new RuntimeException("Random number generator error.");
    }
}