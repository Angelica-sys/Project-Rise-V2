package code.controller;

import code.view.startMenu.StartingScreen;

/**
 * Used to start the program.
 * @author Tor Stenfeldt
 * @date 2021-03-18
 */
public class Start {
    public static void main(String[] args) {
        StartingScreen su = new StartingScreen();
        su.initializeGUI();
    }
}
