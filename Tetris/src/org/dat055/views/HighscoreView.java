package org.dat055.views;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dat055.GameboardController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class HighscoreView {
    private final String SERVER_URL = "http://localhost:8080";

    @FXML VBox scoreContainer;
    @FXML Button backButton;
    VBox scoreList;

    public HighscoreView() {
        URL url;
        HttpURLConnection conn;
        JsonObject scores = null;

        this.scoreList = new VBox();

        // Fetch JSON object from score server
        try {
            url = new URL(SERVER_URL);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            JsonParser jp = new JsonParser();
            JsonElement element = jp.parse(new InputStreamReader((InputStream)conn.getContent()));
            scores = element.getAsJsonObject();
            System.out.println(scores.toString());
        } catch (IOException e) {
            // Score server is unreachable, show error message instead of highscores
            Label errorLabel = new Label("Sorry, the highscore server seems to be down.");
            errorLabel.getStyleClass().add("errorLabel");
            this.scoreList.getChildren().add(errorLabel);
            return;
        }

        // Loop over JSON object and print highscores
        Iterator<String> names = scores.keySet().iterator();
        while(names.hasNext()) {
            String name = names.next();
            HBox container = new HBox();
            Label nameLabel = new Label(name + ": ");
            Label scoreLabel = new Label(scores.get(name).toString());
            nameLabel.getStyleClass().add("highscoreNameLabel");
            scoreLabel.getStyleClass().add("highscoreScoreLabel");
            container.getChildren().add(nameLabel);
            container.getChildren().add(scoreLabel);
            this.scoreList.getChildren().add(container);
        }
    }

    @FXML public void initialize() {
        scoreContainer.getChildren().add(this.scoreList);
        this.backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showMenu(event));
    }

    public void showMenu(MouseEvent event) {
        Stage rootStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_view.fxml"));
        loader.setController(new MenuView(new GameboardController(10, 20)));
        try {
            rootStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
