package org.yates.utils.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.yates.utils.InputHandler;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputHandlerTest {

    @AfterEach
    void reset() throws IOException {
        System.setIn(System.in);
    }

    @Test
    void query() throws IOException {
        // Creating mock input stream
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine())
                .thenReturn("y")
                .thenReturn("n");


        InputHandler inputHandler = InputHandler.builder()
                .validator(in -> in.equals("y") || in.equals("n"))
                .prompt("Do you like cookies with milk? (Y/n): ")
                .errorMessage("Please reply with 'Y' or 'N'")
                .setReader(mockReader)
                .build();
        assertEquals("y", inputHandler.query());

        inputHandler.setPrompt("Do you like oranges? (Y/n): ");
        assertEquals("n", inputHandler.query());
    }

    @Test
    void queryWords() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine())
                .thenReturn("Portugal")
                .thenReturn("Washington State")
                .thenReturn("Washington DC");


        InputHandler inputHandler = InputHandler.builder()
                .validator(in -> in.equals("washington dc") || in.equals("n"))
                .prompt("What's the capitol of the USA? ")
                .errorMessage("Incorrect!")
                .setReader(mockReader)
                .build();

        assertEquals("washington dc", inputHandler.query());
    }
}