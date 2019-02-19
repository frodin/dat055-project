package org.dat055;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String TITLE = "Tetris";
        final int WINDOW_WIDTH = 600;
        final int WINDOW_HEIGHT = 400;

        Parent mainMenu = FXMLLoader.load(getClass().getResource("views/main_menu.fxml"));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(mainMenu));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
