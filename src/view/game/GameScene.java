package view.game;

import controller.game.GameController;
import engine.Game;
import engine.GameManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import view.game.components.Alerts;
import view.game.components.BoardView;
import view.game.components.Hands;
import view.game.components.UserPromt;

import java.io.IOException;

public class GameScene {
    private final StackPane root;
    private final Scene scene;
    private final GameManager game;
    private final Hands hands;
    private final GameController gc;
    private final BoardView boardView;
    public GameScene(GameManager game, Stage primaryStage) {
        this.game = game;
        root = new StackPane();
        root.setPrefSize(1920, 1080);
        scene = new Scene(root, 1920, 1080);
        Alerts alerts = new Alerts();
        gc = new GameController((Game) game, this, alerts, primaryStage);
        hands = new Hands(game, gc);
        try {
            boardView = new BoardView(((Game)game).getBoard(), game, gc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserPromt us = new UserPromt(gc);
        us.renderPlayerData(game);
        root.getChildren().add(createBoardDesign());
        root.getChildren().add(boardView.getRoot());
        root.getChildren().add(createBoardDesign());
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(hands.getRoot());
        root.getChildren().add(us.getRoot());
        root.getChildren().add(alerts.getRoot());

        alerts.displayMessage("Welcome to Dark Jackroo!");
        root.setStyle(
                "-fx-padding: 30;"
        );
        root.setAlignment(Pos.CENTER);
        scene.setOnKeyPressed(e -> {
            System.out.println("Pressed: " + e.getCode());
            if (e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ALT) {
                // Handle space key press
                System.out.println("Space key pressed");
               gc.AutoField();
            }
        });
        root.setBackground(new Background(getBg("background.png")));
        primaryStage.setMaximized(true);
        root.requestFocus();

    }

    private BorderPane createBoardDesign() {
        BorderPane boardDesign = new BorderPane();
        Circle glassCircle = new Circle(400);  // Radius

        // Purplish semi-transparent fill using RadialGradient
        Stop[] stops = new Stop[] {
                new Stop(0, Color.rgb(200, 150, 255, 0.45)),  // light purple
                new Stop(1, Color.rgb(100, 50, 150, 0.15))    // deeper purple
        };
        RadialGradient purpleGradient = new RadialGradient(
                0,
                0,
                0.3, 0.3,
                1,
                true,
                CycleMethod.NO_CYCLE,
                stops
        );
        glassCircle.setFill(purpleGradient);

        // Stroke with soft purple glow
        glassCircle.setStroke(Color.rgb(220, 180, 255, 0.7));
        glassCircle.setStrokeWidth(2);

        // Gaussian Blur to soften edges
        GaussianBlur blur = new GaussianBlur(4);

        // Inner shadow to give depth
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(5);
        innerShadow.setColor(Color.rgb(60, 0, 90, 0.25));

        // Blend both effects together
        Blend blend = new Blend(BlendMode.MULTIPLY, innerShadow, blur);
        glassCircle.setEffect(blend);
        glassCircle.setOpacity(0.1);
        boardDesign.setCenter(glassCircle);
        boardDesign.setPickOnBounds(false);
        boardDesign.setMouseTransparent(true);
        glassCircle.setPickOnBounds(false);
        return  boardDesign;
    }

    public static BackgroundImage getBg(String item) {
        Image image = new Image("file:Assets/"+item);
        System.out.println(image.getHeight());
        return new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, // Repeat X
                BackgroundRepeat.NO_REPEAT, // Repeat Y
                BackgroundPosition.CENTER,  // Position
                new BackgroundSize(
                        BackgroundSize.AUTO, // width
                        BackgroundSize.AUTO, // height
                        false, false,        // widthAsPercentage, heightAsPercentage
                        false, true         // contain, cover
                )
        );
    }

    public void updateHands() {
        hands.updateHands(game, gc);
    }

    public Scene getScene() {
        return scene;
    }

    public void updateBoard() {
        boardView.initBoard(((Game) game).getBoard(), game);
    }


}
