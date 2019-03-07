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

/**
 * Menuview takes care of the visual aspects of the
 * menu in the game
 *
 * @author
 * @version
 */
public class MenuView {
    GameboardController gameBoardController;
    @FXML Button newGameButton;

    public MenuView(GameboardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @FXML
    public void initialize() {
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newGame(event));
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
}
