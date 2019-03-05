package org.dat055;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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



        EventHandler keyHandler = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent key){
                if (key.getCode() == KeyCode.UP && gameBoardController.canWeRotate()) {
                    gameBoardController.rotateTetromino();
                }
                if (key.getCode() == KeyCode.LEFT && gameBoardController.canMove('l')) {
                    System.out.println("You moved LEFT");
                    gameBoardController.moveLeft();
                }
                if (key.getCode() == KeyCode.RIGHT && gameBoardController.canMove('r')) {
                    System.out.println("You moved RIGHT");
                    gameBoardController.moveRight();
                }
                if (key.getCode() == KeyCode.DOWN && gameBoardController.canMove('d')) {
                    System.out.println("You moved DOWN");
                    gameBoardController.moveDown();
                }

            }
        };

        EventHandler startKeyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.O){
                    System.out.println("You started the game");
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
                    System.out.println("You paused the game");
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
