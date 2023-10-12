package org.yates.roshambo.player.tests;

import org.junit.jupiter.api.Test;
import org.yates.roshambo.Move;
import org.yates.roshambo.player.HumanPlayer;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HumanPlayerTest {

    @Test
    void getMove() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine())
                .thenReturn("Test")
                .thenReturn("r")
                .thenReturn("p")
                .thenReturn("s")
                .thenReturn("R")
                .thenReturn("P")
                .thenReturn("S")
                .thenReturn("invalid")
                .thenReturn("r");

        HumanPlayer hp = HumanPlayer.getHumanPlayer(mockReader);
        assertEquals(Move.ROCK, hp.getMove());
        assertEquals(Move.PAPER, hp.getMove());
        assertEquals(Move.SCISSORS, hp.getMove());
        assertEquals(Move.ROCK, hp.getMove());
        assertEquals(Move.PAPER, hp.getMove());
        assertEquals(Move.SCISSORS, hp.getMove());
        assertEquals(Move.ROCK, hp.getMove());

    }
}