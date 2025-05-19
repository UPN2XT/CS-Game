import javafx.application.Application;
import javafx.stage.Stage;
import view.mainMenu.Menu;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Menu menu = new Menu(primaryStage);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }
    public static void main(String[]args){
        launch(args); }
}
