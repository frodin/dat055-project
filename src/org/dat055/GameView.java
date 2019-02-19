package org.dat055;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameView extends Application {
    final String TITLE = "Tetris";
    private Gameboard gameBoard;

    /**
     * Constructor.
     *
     * @param gameBoard The GameBoard to draw.
     */

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        BorderPane root = new BorderPane();
        GridPane field = new GridPane();
        this.gameBoard = new Gameboard(10, 20);

        // Loop over all cells and create javafx Rectangles
        gameBoard.getGameboard().forEach((k, v) -> {
            Rectangle rect = new Rectangle();
            rect.setFill(Color.web(v.getColor()));
            field.add(rect, k.getXPos(), k.getYPos());
        });

        root.getChildren().add(field);

        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
