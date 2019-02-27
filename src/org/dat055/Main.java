package org.dat055;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.dat055.views.MenuView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String TITLE = "Tetris";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/menu_view.fxml"));
        GameboardController gameBoardController = new GameboardController(10, 20);
        MenuView menuController = new MenuView(gameBoardController);
        loader.setController(menuController);
        primaryStage.setTitle(TITLE);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if(key.getCode() == KeyCode.UP) gameBoardController.rotateTetromino();

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.LEFT && gameBoardController.canMove()) {
                System.out.println("You moved LEFT");
                gameBoardController.moveLeft();
            }
            if(key.getCode()== KeyCode.RIGHT && gameBoardController.canMove()) {
                System.out.println("You moved RIGHT");
                gameBoardController.moveRight();
            }
            if(key.getCode()== KeyCode.DOWN && gameBoardController.canMove()) {
                System.out.println("You moved DOWN");
                gameBoardController.moveDown();
            }
        });
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
