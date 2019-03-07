package org.dat055;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.dat055.controller.GameboardController;
import org.dat055.view.MenuView;

/**
 * Main class initiates the environment needed for the application.
 *
 * @author Philip Hellberg
 * @version 2019-03-06
 */


public class Main extends Application {
    private static Stage primaryStage; // **Declare static Stage**

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String TITLE = "Tetris";
        setPrimaryStage(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/menu_view.fxml"));
        GameboardController gameBoardController = new GameboardController(10, 20);
        MenuView menuController = new MenuView(gameBoardController);
        loader.setController(menuController);
        primaryStage.setTitle(TITLE);


        EventHandler keyHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent key) {
                if (key.getCode() == KeyCode.UP && gameBoardController.canWeRotate()) {
                    gameBoardController.rotateTetromino();
                }
                if (key.getCode() == KeyCode.LEFT && gameBoardController.canMove('l')) {

                    gameBoardController.moveLeft();
                }
                if (key.getCode() == KeyCode.RIGHT && gameBoardController.canMove('r')) {

                    gameBoardController.moveRight();
                }
                if (key.getCode() == KeyCode.DOWN && gameBoardController.canMove('d')) {

                    gameBoardController.moveDown();
                }

            }
        };

        EventHandler startKeyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.O) {

                    gameBoardController.start();
                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                    primaryStage.removeEventHandler(KeyEvent.KEY_PRESSED, this);
                }
            }
        };

        EventHandler pauseKeyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.P) {

                    gameBoardController.pause();
                    primaryStage.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, startKeyHandler);

                }

            }
        };

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, pauseKeyHandler);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
