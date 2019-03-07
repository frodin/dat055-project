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



        EventHandler startKeyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.O) {
                    System.out.println("You started the game");
                    gameBoardController.start();
                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, gameBoardController.getKeyHandler());
                    primaryStage.removeEventHandler(KeyEvent.KEY_PRESSED, this);
                }
            }
        };

        EventHandler pauseKeyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.P) {
                    System.out.println("You paused the game");
                    gameBoardController.pause();
                    primaryStage.removeEventHandler(KeyEvent.KEY_PRESSED, gameBoardController.getKeyHandler());
                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, startKeyHandler);
                }
            }
        };

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, pauseKeyHandler);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, gameBoardController.getKeyHandler());

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
