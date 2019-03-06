package org.dat055.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HighscoreView {
    @FXML VBox highscoreList;

    public HighscoreView() {
    }

    @FXML public void initialize() {
        Label newlabel = new Label("Hello");
        highscoreList.getChildren().add(newlabel);
    }
}
