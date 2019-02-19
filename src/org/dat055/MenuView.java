package org.dat055;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class MenuView extends Application {

    private Scene menuScene;
    private Button startButton;

    /**
     *
     * @param window the window to be opened.
     * @throws Exception
     */
    public void start(Stage window) throws Exception {

        window.setTitle("Main menu, Tetris");

        startButton = new Button();
        startButton.setText("Play Tetris");

        StackPane layout =  new StackPane();
        layout.getChildren().add(startButton);

        menuScene = new Scene(layout, 300,500);
        window.setScene(menuScene);
        window.show();
        startButton.setOnAction(event -> {

                /*
                // GameView should return a Scene object.
                // That scene is later set upon the already open window.
                GameView gameView = new GameView();
                window.setScene(gameView);
                */

        });
    }
}
