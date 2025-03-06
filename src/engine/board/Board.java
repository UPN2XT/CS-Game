package engine.board;

import engine.GameManager;
import java.util.ArrayList;
import model.Colour;
import java.util.Random;

/**
 * The Board class implements the BoardManager interface and represents the game board.
 */
public class Board implements BoardManager {
    private final GameManager gameManager;
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
    private int splitDistance;
    private final Random random = new Random();

    /**
     * Constructs a new Board instance.
     *
     * @param colourOrder the order of colours for the safe zones
     * @param gameManager the game manager instance
     */
    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.gameManager = gameManager;
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.splitDistance = 3;
        initialiseSafeZones(colourOrder);
        initTrack();
    }

    /**
     * Initializes the safe zones with the given colour order.
     *
     * @param colourOrder the order of colours for the safe zones
     */
    private void initialiseSafeZones(ArrayList<Colour> colourOrder) {
        for (Colour colour : colourOrder)
            safeZones.add(new SafeZone(colour));
    }

    /**
     * Initializes the track with cells.
     */
    private void initTrack() {
        /*
         * Track can be divided into 4 parts
         * each part has a base cell, 22 normal cells, an entry cell and a normal cell
         * in this order we create this sequence four times for each part of the track
         */
        for (int i = 0; i < 4; i++) {
            track.add(new Cell(CellType.BASE));
            for (int j = 0; j < 22; j++)
                track.add(new Cell(CellType.NORMAL));
            track.add(new Cell(CellType.ENTRY));
            track.add(new Cell(CellType.NORMAL));
        }
        setTraps();
    }

    /**
     * Sets traps on the track.
     */
    private void setTraps() {
        for (int i = 0; i < 8; i++)
            assignTrapCell();
    }

    /**
     * Assigns a trap to a random normal cell on the track.
     */
    private void assignTrapCell() {
        // search for a random normal cell and assign it as a trap
        int x;
        do {
            x = random.nextInt(track.size());
        } while (track.get(x).getCellType() != CellType.NORMAL || track.get(x).isTrap());
        track.get(x).setTrap(true);
    }

    // getter methods

    /**
     * Gets the track of cells.
     *
     * @return an ArrayList of Cell representing the track
     */
    public ArrayList<Cell> getTrack() {
        return track;
    }

    /**
     * Gets the split distance.
     *
     * @return the split distance
     */
    public int getSplitDistance() {
        return splitDistance;
    }

    /**
     * Sets the split distance.
     *
     * @param splitDistance the new split distance
     */
    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }

    /**
     * Gets the safe zones.
     *
     * @return an ArrayList of SafeZone representing the safe zones
     */
    public ArrayList<SafeZone> getSafeZones() {
        return safeZones;
    }
}