package org.yates.roshambo;

public enum Move {
    ROCK, PAPER, SCISSORS;

    private Outcome rock(Outcome x) {
        if (x == Outcome.WIN)
            System.out.println("Rock smashes Scissors!");
        System.out.println("Paper covers Rock!");
        return x;
    }

    private Outcome paper(Outcome x) {
        if (x == Outcome.WIN)
            System.out.println("Paper covers Rock!");
        System.out.println("Scissors cut Paper!");
        return x;
    }

    private Outcome scissors(Outcome x) {
        if (x == Outcome.WIN)
            System.out.println("Scissors cut Paper!");
        System.out.println("Rock smashes Scissors!");
        return x;
    }

    public Outcome whoWins(Move otherMove) {
        if (this == otherMove) {
            System.out.println(this + " vs " + this + "!!");
            return Outcome.DRAW;
        }

        return switch (this) {
            case ROCK -> (otherMove == SCISSORS) ? rock(Outcome.WIN) : rock(Outcome.LOSS);
            case PAPER -> (otherMove == ROCK) ? paper(Outcome.WIN) : paper(Outcome.LOSS);
            case SCISSORS -> (otherMove == PAPER) ? scissors(Outcome.WIN) : scissors(Outcome.LOSS);
        };
    }
}
