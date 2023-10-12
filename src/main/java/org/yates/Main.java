package org.yates;

import org.yates.roshambo.Game;
import org.yates.utils.InputHandler;

public class Main {
    public static void main(String[] args) {
//        Game game = new Game();
//        game.run();
        InputHandler im = InputHandler.builder()
                .prompt("test")
                .validator(in -> true)
                .build();
        System.out.println(im.query());
    }
}