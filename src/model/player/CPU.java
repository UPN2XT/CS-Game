package model.player;

import engine.board.BoardManager;
import model.Colour;

/**
 * The CPU class represents a computer-controlled player.
 * It extends the Player class and inherits its properties and behaviors.
 */
public class CPU extends Player {
    private final BoardManager boardManager;

    /**
     * Constructs a new CPU player with the specified attributes.
     *
     * @param name         the name of the CPU player
     * @param colour       the colour associated with the CPU player
     * @param boardManager the board manager used by this player
     */
    public CPU(String name, Colour colour, BoardManager boardManager) {
        super(name, colour);
        this.boardManager = boardManager;
    }
}