package org.dat055.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.dat055.controller.GameboardController;

import java.io.IOException;

/**
 * Menuview takes care of the visual aspects of the
 * menu in the game
 *
 * @author Max Hansson
 * @version 2019-03-07
 */
public class MenuView {
    GameboardController gameBoardController;
    @FXML Button newGameButton;
    @FXML Button highscoreButton;

    public MenuView(GameboardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    /**
     * Initialize mouseevent for our buttons on the menu
     */
    @FXML
    public void initialize() {
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newGame(event));
        highscoreButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showHighscore(event));
    }

    /**
     * Starts the new game
     *
     * @param event that stores the actual mouse click
     */
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

    /**
     * Shows the highscore
     *
     * @param event that stores the mouse click
     */
    @FXML
    public void showHighscore(MouseEvent event) {
        Stage rootStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // lol java
        FXMLLoader loader = new FXMLLoader(getClass().getResource("highscore_view.fxml"));
        loader.setController(new HighscoreView(this.gameBoardController));
        try {
            rootStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
