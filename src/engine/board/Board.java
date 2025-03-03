package engine.board;

import engine.GameManager;
import java.util.ArrayList;
import model.Colour;
import java.util.Random;

public class Board implements BoardManager{
    private final GameManager gameManager;
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
    private int splitDistance;
    private final Random random = new Random();

   public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.gameManager = gameManager;
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.splitDistance = 3;
       initialiseSafeZones(colourOrder);
       initTrack();
    }

    private void initialiseSafeZones(ArrayList<Colour> colourOrder) {
       for (Colour colour : colourOrder)
           safeZones.add(new SafeZone(colour));
    }

    private void initTrack() {
       for (int i = 0; i < 4; i++) {
           track.add(new Cell(CellType.BASE));
           for (int j = 0; j < 22; j++)
               track.add(new Cell(CellType.NORMAL));
           track.add(new Cell(CellType.ENTRY));
           track.add(new Cell(CellType.NORMAL));
       }
       setTraps();
    }

    private void setTraps() {
        for (int i = 0; i < 8; i++)
            assignTrapCell();
    }

    private void assignTrapCell() {
        int x;
        do {
            x = random.nextInt(track.size());
        } while(track.get(x).getCellType() != CellType.NORMAL || track.get(x).isTrap());
        track.get(x).setTrap(true);
    }

    public ArrayList<Cell> getTrack() {
       return track;
    }

    public int getSplitDistance() {
       return splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
       this.splitDistance = splitDistance;
    }

    public ArrayList<SafeZone> getSafeZones() {
       return safeZones;
    }

}
