package org.dat055.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {

    public MainMenu() {

    }

    @FXML
    public void newGame(MouseEvent event) throws IOException {
        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent game = FXMLLoader.load(getClass().getResource("game.fxml"));
        rootStage.setScene(new Scene(game));
    }
}
