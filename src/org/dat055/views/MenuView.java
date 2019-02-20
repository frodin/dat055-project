package org.dat055.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.dat055.Gameboard;

import java.io.IOException;

public class MenuView {
    Gameboard gameBoard;
    @FXML Button newGameButton;

    public MenuView(Gameboard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @FXML
    public void initialize() {
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newGame(event));
    }

    @FXML
    public void newGame(MouseEvent event) {
        Stage rootStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // lol java
        Parent game = null;
        try {
            game = FXMLLoader.load(getClass().getResource("game_view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootStage.setScene(new Scene(game));
    }


}
