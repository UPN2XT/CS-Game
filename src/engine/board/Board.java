package engine.board;

import engine.Game;
import engine.GameManager;
import java.util.ArrayList;

import exception.*;
import model.Colour;
import model.player.Marble;

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

    private ArrayList<Cell> getSafeZone(Colour colour) {
        for (SafeZone safeZone : safeZones) {
            if (safeZone.getColour() == colour) {
                return safeZone.getCells();
            }
        }
        return null;
    }

    private int getPositionInPath(ArrayList<Cell> path, Marble marble) {
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).getMarble() == marble) {
                return i;
            }
        }
        return -1;
    }


    private int getColorPos(Colour colour) {
        for (int i = 0; i < 4; i++)
            if (safeZones.get(i).getColour() == colour) {
                return i;
            }
        return -1;
    }

    private int getBasePosition(Colour colour) {
        int i = getColorPos(colour);
        if (i == -1)
            return -1;
        return (i) * 25;
    }


    private int getEntryPosition(Colour colour) {
        int i = getColorPos(colour);
        if (i == -1)
            return -1;
        return (i*25 -2 + 100)%100;
    }

    private ArrayList<Cell> getPath(int i, int steps, boolean inTrack, int entryPos, boolean isMine, Marble marble) {
        ArrayList<Cell> path = new ArrayList<>();
        ArrayList<Cell> safe =  getSafeZone(marble.getColour());
        path.add(inTrack? track.get(i):safe.get(i));
        boolean normal = steps != 4;
        int z = i;
        for (int j = 1; j <= steps; j++) {
            if (!normal) {
                z = z - 1 < 0? track.size()-1 : z - 1;
                path.add(track.get(z));
                continue;
            }
            z = (z + 1) % track.size();
            if (inTrack) {
                path.add(track.get(z));
                if (isMine && z == entryPos) {
                    inTrack = false;
                    z = -1;
                }

            }
            else
                path.add(safe.get(z));

        }
        return path;
    }

    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws
            IllegalMovementException {
        ArrayList<Cell> path = new ArrayList<>();
        boolean inTrack = true;
        int i = getPositionInPath(this.track, marble);
        if (i == -1) {
            inTrack = false;
            i = getPositionInPath(getSafeZone(marble.getColour()), marble);
            if (i == -1)
                throw new IllegalMovementException("Marble not on track or safe zone");
        }
        if (!inTrack && steps == 4)
            throw new IllegalMovementException("Cannot move backwards in Safe Zone");
        boolean isMine = marble.getColour() == gameManager.getActivePlayerColour();
        int entryPos = getEntryPosition(marble.getColour());
        if (isMine) {
            if (inTrack && entryPos - i + 4 < steps) {
                throw new IllegalMovementException("Rank of card played is too high 1");
            }
            else if (!inTrack && 3 - i < steps)
                throw new IllegalMovementException("Rank of card played is too high 2");
        }
        else {
            if (!inTrack) throw new IllegalMovementException("Rank of card played is too high 3");
        }
        return getPath(i, steps, inTrack, entryPos, isMine, marble);
    }

    // TODO make sure of the path vs target and safe zone entrance
    private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean
            destroy) throws IllegalMovementException {
        int count = 0;
        boolean isMine = marble.getColour() == gameManager.getActivePlayerColour();
        for (int i = 1; i < fullPath.size(); i++) {
            Cell cell = fullPath.get(i);
            if (cell.getMarble() == marble)
                continue;
            if (cell.getCellType() == CellType.SAFE) throw new IllegalMovementException("Cannot pass in safe zone");
            if (cell.getMarble() == null) continue;
            if (!destroy) {
                if (cell.getMarble().getColour() == gameManager.getActivePlayerColour()) throw new IllegalMovementException("Cannot pass your own marble");
                if (i != fullPath.size() - 1)
                    count++;
                if (count > 1) throw new IllegalMovementException("can not pass more than one marble");
                if (cell.getCellType() == CellType.ENTRY && isMine) {
                    throw new IllegalMovementException("Cannot move to entry cell");
                }
            }
            if (cell.getCellType() == CellType.BASE && track.get(getBasePosition(cell.getMarble().getColour())) == cell) {
                throw new IllegalMovementException("Cannot move to base cell");
            }
        }
    }

    private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy)
            throws IllegalDestroyException {
        if (destroy)
            for (int i = 1; i < fullPath.size(); i++)
                if (fullPath.get(i).getMarble() != null) {
                    Marble m = fullPath.get(i).getMarble();
                    destroyMarble(m);
                }
        else
            if (fullPath.get(fullPath.size() - 1).getMarble() != null) {
                Marble m = fullPath.get(fullPath.size() - 1).getMarble();
                if (m.getColour() == marble.getColour()) {
                    throw  new IllegalDestroyException();
                }
                destroyMarble(m);
            }
        if (fullPath.get(fullPath.size() - 1).isTrap())
            destroyMarble(marble);
        else
            fullPath.get(fullPath.size() - 1).setMarble(marble);
        fullPath.get(0).setMarble(null);
    }

    private void validateSwap(Marble marble_1, Marble marble_2) throws
            IllegalSwapException {
            Marble mine = marble_1.getColour() == gameManager.getActivePlayerColour() ? marble_1: marble_2;
            Marble other = mine == marble_1? marble_2: marble_1;
            int pos =  getPositionInPath(track, other);
            if (getPositionInPath(track, mine) == -1 || pos == -1)
                throw new IllegalSwapException("Marble not on track");
            if (pos == getBasePosition(other.getColour()))
                throw new IllegalSwapException("The opponent’s marble is safe in its own Base Cell.");
    }

    private void validateDestroy(int positionInPath) throws IllegalDestroyException {
        if (positionInPath == -1)
            throw new IllegalDestroyException("Marble not on track");

        if (track.get(positionInPath).getCellType() == CellType.BASE && getBasePosition(track.get(positionInPath).getMarble().getColour()) == positionInPath) {
            throw new IllegalDestroyException("The opponent’s marble is safe in its own Base Cell.");
        }
    }

    private void validateFielding(Cell occupiedBaseCell) throws CannotFieldException {
        if (occupiedBaseCell.getMarble() == null) return;
        if (occupiedBaseCell.getMarble().getColour() == gameManager.getActivePlayerColour()) {
            throw new CannotFieldException("Cannot field in own base cell");
        }
    }

    private void validateSaving(int positionInSafeZone, int positionOnTrack) throws
            InvalidMarbleException {
        if (positionInSafeZone != -1)
            throw new InvalidMarbleException("Marble is in safe zone");
        if (positionOnTrack == -1)
            throw new InvalidMarbleException("Marble is not track");
    }

    public void moveBy(Marble marble, int steps, boolean destroy) throws
            IllegalMovementException, IllegalDestroyException {
        ArrayList<Cell> fullPath = validateSteps(marble, steps);
        validatePath(marble, fullPath, destroy);
        move(marble, fullPath, destroy);
    }

    public void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException {
        validateSwap(marble_1, marble_2);
        int pos1 = getPositionInPath(track, marble_1);
        int pos2 = getPositionInPath(track, marble_2);
        track.get(pos1).setMarble(marble_2);
        track.get(pos2).setMarble(marble_1);
    }

    public void destroyMarble(Marble marble) throws IllegalDestroyException {
        int positionInPath = getPositionInPath(track, marble);
        validateDestroy(positionInPath);
        track.get(positionInPath).setMarble(null);
        gameManager.sendHome(marble);
    }

    public void sendToBase(Marble marble) throws CannotFieldException,
            IllegalDestroyException {
        int basePosition = getBasePosition(marble.getColour());
        Cell c = track.get(basePosition);
        validateFielding(c);
        if (c.getMarble() != null)
            destroyMarble(c.getMarble());
        c.setMarble(marble);
    }

    public void sendToSafe(Marble marble) throws InvalidMarbleException {
        int pos = getPositionInPath(track, marble);
        validateSaving(0, pos);
        ArrayList<Cell> safe = getSafeZone(marble.getColour());
        do {
            int x = random.nextInt(safe.size());
            if (safe.get(x).getMarble() == null) {
                safe.get(x).setMarble(marble);
                break;
            }
        } while (true);
        track.get(pos).setMarble(null);
    }

    public ArrayList<Marble> getActionableMarbles() {
        ArrayList<Marble> marbles = new ArrayList<>();
        for (Cell cell : track) {
            if (cell.getMarble() != null && cell.getMarble().getColour() == gameManager.getActivePlayerColour()) {
                marbles.add(cell.getMarble());
            }
        }
        SafeZone safe = null;
        for (SafeZone zone : safeZones) {
            if (zone.getColour() == gameManager.getActivePlayerColour()) {
                safe = zone;
                break;
            }
        }
        for (Cell cell : safe.getCells()) {
            if (cell.getMarble() != null && cell.getMarble().getColour() == gameManager.getActivePlayerColour()) {
                marbles.add(cell.getMarble());
            }
        }
        return marbles;
    }
}