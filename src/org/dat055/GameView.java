package org.dat055;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameView extends Application {
    final String TITLE = "Tetris";

    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        Button btn = new Button();
        btn.setText("Hello world");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }
}
