package org.dat055.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.dat055.Gameboard;

import java.io.IOException;

public class MenuView {
    Gameboard gameBoard;

    public MenuView(Gameboard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @FXML
    public void newGame(MouseEvent event) throws IOException {
        Stage rootStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // lol java
        Parent game = FXMLLoader.load(getClass().getResource("game_view.fxml"));
        rootStage.setScene(new Scene(game));
    }


}
