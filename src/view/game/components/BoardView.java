package view.game.components;

import controller.game.GameController;
import engine.Game;
import engine.GameManager;
import engine.board.Board;
import engine.board.BoardManager;
import engine.board.Cell;
import engine.board.SafeZone;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;
import model.player.Marble;
import model.player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class BoardView {
    private final VBox root;
    final Pane grid;
    final int rows = 41;
    final int cols = 41;
    final int cellSize = 25;
    final GameController gc;

    public BoardView(BoardManager bm, GameManager gm, GameController gc) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardView.fxml"));
        root = new VBox();
        HBox y = new HBox();
        root.getChildren().add(y);
        root.setAlignment(Pos.CENTER);
        y.setAlignment(Pos.CENTER);
        grid = new Pane();
        grid.setPrefSize(cols * cellSize, rows * cellSize);
        grid.setOnMouseClicked(event -> {
            System.out.println("Clicked on board");
            //gc.selectMarble(null);
        });
        y.getChildren().add(grid);
        this.gc = gc;
        initBoard(bm, gm);
    }

    private void insertElement(int row, int col, Circle circle) {
        circle.setTranslateX(col * cellSize - cellSize / 2.0);
        circle.setTranslateY(row * cellSize - cellSize / 2.0);
        grid.getChildren().add(circle);
    }

    private Color getColor (Colour c) {
        Color color;
        switch (c) {
            case RED:
                color = Color.RED;
                break;
            case BLUE:
                color = Color.BLUE;
                break;
            case YELLOW:
                color = Color.YELLOW;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
        return color;
    }

    private void initHomes(ArrayList<Player> players) {
        ArrayList<int[]> cellsLocations = new ArrayList<>();
        // ref points  p1: (28, 13) p2: (14, 13) p3: (14, 29) p4: (28, 29)
        cellsLocations.add(createPos(28, 13));
        cellsLocations.add(createPos(14, 13));
        cellsLocations.add(createPos(14, 29));
        cellsLocations.add(createPos(28, 29));
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int[] cellLocation = cellsLocations.get(i);
            int row = cellLocation[0];
            int col = cellLocation[1];
            initHomeZone(row, col, p);
        }
    }

    private void initHomeZone(int row, int col, Player p) {
        ArrayList <Circle> circles = new ArrayList <>();
        Color c = getColor(p.getColour());
        for (int i = 0; i < p.getMarbles().size(); i++) {
            Circle circle = new Circle();
            circle.setRadius(12.5);
            if (p.getMarbles().get(i) != null) {
                circle.setFill(c);
                System.out.println(p.getMarbles().get(i).toString());
            }
            else
            {
                circle.setStroke(c);
                circle.setFill(Color.TRANSPARENT);
                circle.setStrokeWidth(2);
            }
            circles.add(circle);
        }
        if (circles.size() > 0) insertElement(row+1, col+1, circles.get(0));
        if (circles.size() > 1) insertElement(row+1, col-1, circles.get(1));
        if (circles.size() > 2)insertElement(row-1, col+1, circles.get(2));
        if (circles.size() > 3)insertElement(row-1, col-1, circles.get(3));
    }

    public void initBoard(BoardManager bm, GameManager gm) {
        grid.getChildren().clear();
        ArrayList<SafeZone> safeZones = ((Board)bm).getSafeZones();
        initSafeZone(false, 2,safeZones.get(0));
        initSafeZone(true, -2,safeZones.get(1));
        initSafeZone(false, -2,safeZones.get(2));
        initSafeZone(true, 2,safeZones.get(3));
        initTrack(((Board) bm).getTrack());
        initHomes(((Game) gm).getPlayers());
    }


    private void initSafeZone(boolean isHorzontal, int mult, SafeZone s) {
        ArrayList<int[]> cellsLocations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (!isHorzontal) cellsLocations.add(new int[]{(i + 2) * mult+ 21, 21});
            else cellsLocations.add(new int[]{21,mult * (i+2)+ 21});
        }
        initSafeZone(cellsLocations, s.getCells() ,s.getColour());
    }


    private void initSafeZone(ArrayList<int[]> cellsLocations, ArrayList<Cell> cells,Colour c) {
        Color color = getColor(c);
        int i = 0;
        for (int[] cellLocation : cellsLocations) {
            int row = cellLocation[0];
            int col = cellLocation[1];
            Circle circle = new Circle();
            circle.setRadius(12.5);
            if (cells.get(i++).getMarble() != null)
                circle.setFill(color);
            else
                // dont fell only the edge should have the color
            {
                circle.setStroke(color);
                circle.setFill(Color.TRANSPARENT);
                circle.setStrokeWidth(2);
            }
            insertElement(row, col, circle);
        }
    }

    private void initTrack(ArrayList<Cell> track) {
        ArrayList<int[]> cellsLocations = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(33-i, 19));
        for (int i = 0; i < 11; i++)
            cellsLocations.add(createPos(23, 19-i));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(23-i, 8));
        for (int i = 0; i < 11; i++)
            cellsLocations.add(createPos(19, 8+i));
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(19-i, 19));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(9, 19+i));
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(9+i, 23));
        for (int i = 0; i < 11; i++)
            cellsLocations.add(createPos(19, 23+i));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(19+i, 34));
        for (int i = 0; i < 11; i++)
            cellsLocations.add(createPos(23, 34-i));
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(23+i, 23));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(33, 23-i));

        int j = 0;
        for (int[] cellLocation : cellsLocations) {
            int row = cellLocation[0];
            int col = cellLocation[1];
            Circle circle = new Circle();
            circle.setRadius(11);
            Color color = Color.BLACK;
            int x = j++;
            Marble m = track.get(x).getMarble();
            if (m != null) {
                circle.setFill(getColor(m.getColour()));
                if (gc.getGame().getPlayers().get(0).getSelectedMarbles().contains(m)) {
                    circle.setStroke(Color.ORANGE);
                    circle.setStrokeWidth(3);
                }
                circle.setFill(getColor(m.getColour()));

                circle.setOnMouseClicked(mouseEvent -> {
                    gc.selectMarble(track.get(x).getMarble());
                });
            }
            else {
                circle.setStroke(color);
                circle.setFill(Color.TRANSPARENT);
                circle.setStrokeWidth(2);
            }

            insertElement(row, col, circle);
        }
    }

    private int[] createPos(int row, int col) {
        return new int[]{row, col};
    }

    public Parent getRoot() {
        return root;
    }
}