package org.yates.roshambo;

public enum Move {
    ROCK, PAPER, SCISSORS;

    public Outcome whoWins(Move otherMove) {
        if (this == otherMove)
            return Outcome.DRAW;

        return switch (this) {
            case ROCK -> (otherMove == SCISSORS) ? Outcome.WIN : Outcome.LOSS;
            case PAPER -> (otherMove == ROCK) ? Outcome.WIN : Outcome.LOSS;
            case SCISSORS -> (otherMove == PAPER) ? Outcome.WIN : Outcome.LOSS;
        };
    }
}
