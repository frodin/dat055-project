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
import org.dat055.GameboardController;

import java.io.IOException;

public class MenuView {
    GameboardController gameBoardController;
    @FXML Button newGameButton;
    @FXML Button highscoreButton;

    public MenuView(GameboardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @FXML
    public void initialize() {
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newGame(event));
        highscoreButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showHighscore(event));
    }

    @FXML
    public void newGame(MouseEvent event) {
        Stage rootStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // lol java
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game_view.fxml"));
        loader.setController(new GameView(this.gameBoardController));
        try {
            rootStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showHighscore(MouseEvent event) {
        Stage rootStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // lol java
        FXMLLoader loader = new FXMLLoader(getClass().getResource("highscore_view.fxml"));
        loader.setController(new HighscoreView());
        try {
            rootStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
