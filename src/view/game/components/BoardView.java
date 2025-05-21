package view.game.components;

import controller.game.GameController;
import engine.Game;
import engine.GameManager;
import engine.board.Board;
import engine.board.BoardManager;
import engine.board.Cell;
import engine.board.SafeZone;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.Colour;
import model.card.Card;
import model.card.standard.Standard;
import model.player.Marble;
import model.player.Player;

import java.io.IOException;
import java.util.ArrayList;

import static view.game.components.Hand.getSuit;

public class BoardView {
    private final VBox root;
    final Pane grid;
    final int rows = 41;
    final int cols = 41;
    final int cellSize = 22;
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
        y.getChildren().add(grid);
        this.gc = gc;
        initBoard(bm, gm);
    }

    private void insertElement(double row, int col, Node node) {
        node.setTranslateX(col * cellSize - cellSize / 2.0);
        node.setTranslateY(row * cellSize - cellSize);
        grid.getChildren().add(node);
    }

    public static Color getColor (Colour c) {
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

    private Color getColorLight (Colour c) {
        Color color;
        switch (c) {
            case RED:
                color = new Color(1, 0.5, 0.5, 1);
                break;
            case BLUE:
                color = Color.LIGHTBLUE;
                break;
            case YELLOW:
                color = Color.LIGHTYELLOW;
                break;
            case GREEN:
                color = Color.LIGHTGREEN;
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
            circle.setRadius(cellSize / 2.0);
            if (p.getMarbles().get(i) != null) {
                circle.setFill(c);
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

        // runic style text
        Circle circle = new Circle();
        circle.setRadius(cellSize*1.5);
        circle.setFill(Color.PURPLE);
        // shadow effect
        circle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);");
        circle.setOpacity(0.5);
        BorderPane cardBox;
        ArrayList<Card> fire = ((Game) gm).getFirePit();
        System.out.println("fire size:" + fire.size());
        if (fire.isEmpty())
            cardBox = initFireCard();
        else {
            cardBox = initFireCard(fire.get(fire.size() - 1), fire);
        }
        insertElement(16.5,45, cardBox);
        insertElement(21,21, circle);
    }

    private BorderPane initFireCard() {
        BorderPane cardBox = new BorderPane();
        cardBox.setPrefSize(154, 215.6);
        String style =
                "-fx-background-color: transparent; " +
                        "-fx-background-radius: 12; " +
                        "-fx-border-color: white; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 12; " +
                        "-fx-padding: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);";
        cardBox.setStyle(style);
        Text t = new Text("Fire Pit");
        t.setFill(Color.WHITE);
        // size of 15 px
        t.setStyle(
                "-fx-font-size: 15px; -fx-font-weight: bold;"
        );
        cardBox.setCenter(t);
        return cardBox;
    }

    private BorderPane initFireCard(Card card, ArrayList<Card> fire) {
        BorderPane cardBox = new BorderPane();
        if (card == null)
            return fire.indexOf(card) == 0? initFireCard(): initFireCard(fire.get(fire.indexOf(card) - 1), fire);
        cardBox.setPrefSize(154, 215.6);
        String style =
                "-fx-background-color: white; " +
                        "-fx-background-radius: 12; " +
                        "-fx-border-radius: 12; " +
                        "-fx-padding: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);";
        cardBox.setStyle(style);
        VBox top = new VBox();
        Text id = new Text(Hand.getCardId(card.getName()));
        VBox bottom = new VBox();
        Text id_bt = new Text(id.getText());
        top.setPadding(new Insets(0,0,0,10));
        id.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold;"
        );
        top.getChildren().add(id);
        cardBox.setTop(top);
        if (card instanceof Standard) {
            Text suit = new Text(String.valueOf(getSuit(((Standard) card).getSuit())));
            suit.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
            top.getChildren().add(suit);
            Text suit_bt = new Text(suit.getText());
            suit_bt.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
            bottom.getChildren().add(suit_bt);
            suit_bt.setRotate(180.0);
        }
        bottom.setPadding(new Insets(0,10,0,0));
        id_bt.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold;"
        );
        id_bt.setRotate(180.0);
        bottom.getChildren().add(id_bt);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        cardBox.setBottom(bottom);
        return cardBox;
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
        Color light = getColorLight(c);
        int i = 0;
        for (int[] cellLocation : cellsLocations) {
            int row = cellLocation[0];
            int col = cellLocation[1];
            Circle circle = new Circle();
            circle.setRadius(cellSize / 2.0);
            Marble m = cells.get(i++).getMarble();
            if (m != null) {
                circle.setFill(color);
                if (c == gc.getGame().getPlayers().get(0).getColour()) {
                    if (gc.getSelectMarbles().contains(m)) {
                        circle.setStroke(Color.ORANGE);
                        circle.setStrokeWidth(3);
                    }
                    circle.setFill(getColor(m.getColour()));

                    circle.setOnMouseClicked(mouseEvent -> {
                        gc.selectMarble(m);
                    });
                }

            }
            else
                // dont fell only the edge should have the color
            {
                //circle.setStroke(Color.PURPLE);
                circle.setFill(light);
                circle.setOpacity(0.7);
                circle.setStrokeWidth(2);
            }
            insertElement(row, col, circle);
        }
    }

    private void initTrack(ArrayList<Cell> track) {
        ArrayList<int[]> cellsLocations = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(33-i, 19));
        cellsLocations.add(createPos(23, 20));
        cellsLocations.add(createPos(22, 19));
        for (int i = 0; i < 9; i++)
            cellsLocations.add(createPos(23, 18-i));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(23-i, 9));
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(19, 9+i));
        cellsLocations.add(createPos(20, 19));
        cellsLocations.add(createPos(19, 20));
        for (int i = 0; i < 9; i++)
            cellsLocations.add(createPos(18-i, 19));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(9, 19+i));
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(9+i, 23));
        cellsLocations.add(createPos(19, 22));
        cellsLocations.add(createPos(20, 23));
        for (int i = 0; i < 9; i++)
            cellsLocations.add(createPos(19, 24+i));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(19+i, 33));
        for (int i = 0; i < 10; i++)
            cellsLocations.add(createPos(23, 33-i));
        cellsLocations.add(createPos(22, 23));
        cellsLocations.add(createPos(23, 22));
        for (int i = 0; i < 9; i++)
            cellsLocations.add(createPos(24+i, 23));
        for (int i = 0; i < 4; i++)
            cellsLocations.add(createPos(33, 23-i));

        int j = 0;
        for (int[] cellLocation : cellsLocations) {
            int row = cellLocation[0];
            int col = cellLocation[1];
            Circle circle = new Circle();
            circle.setRadius(11);
            Color color = Color.PURPLE;
            int x = j++;
            Marble m = track.get(x).getMarble();
            if (m != null) {
                circle.setFill(getColor(m.getColour()));
                if (gc.getSelectMarbles().contains(m)) {
                    circle.setStroke(Color.ORANGE);
                    circle.setStrokeWidth(3);
                }
                circle.setFill(getColor(m.getColour()));

                circle.setOnMouseClicked(mouseEvent -> {
                    gc.selectMarble(track.get(x).getMarble());
                });
            }
            else {
                if (x % 25 == 0) {
                    circle.setFill(color);
                    circle.setStroke(getColorLight(gc.getGame().getPlayers().get((int)(x/25)).getColour()));
                }
                else if ((x-23) % 25 == 0) {
                    circle.setFill(getColorLight(gc.getGame().getPlayers().get((int) (((x - 23) / 25) + 1) % 4).getColour()));
                    circle.setStroke(color);
                    circle.setStrokeWidth(2);
                }
                else
                    circle.setFill(color);
                // glass like effect
                circle.setOpacity(0.5);



                circle.setStrokeWidth(2);
            }
            // the circle has a glass like effect  with strong purple shadow
            circle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);");
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