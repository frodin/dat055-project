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
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.UP) gameBoardController.rotateTetromino();
        });
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
