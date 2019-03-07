package org.dat055.views;

import com.google.gson.JsonObject;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.dat055.*;

import java.io.IOException;
import java.io.File;
import java.util.*;

/**
 * GameView takes care of the visual aspects of the game
 *
 * @author Max Hansson
 * @version 2019-03-07
 */
public class GameView implements Observer {
    @FXML private GridPane field;
    private GameboardController gameBoardController;
    private MediaPlayer mediaPlayer;
    //private final String USER_AGENT = "Mozilla/5.0";

    // score counter
    @FXML private HBox scoreArea;
    @FXML private Label scoreLabel;
    @FXML private Label scoreCountLabel;


    @FXML private HBox levelArea;
    @FXML private Label levelCountLabel;
    @FXML private Label levelLabel;
    private Label linesCleared;
    private Label lineClearedCounter;

    // fps counter
    @FXML private Label fpsCounter;
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    // redraw counter
    @FXML private Label redrawCounter;
    private int redraws = 0;

    // change event counter
    @FXML private Label changeCounter;
    private int changeEvents = 0;

    // Width & height of field cells in pixels
    private final static int RECT_WIDTH = 20;
    private final static int RECT_HEIGHT = 20;

    /**
     * Constructor.
     * @param gameBoardController The associated Gameboard model.
     */
    public GameView(GameboardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @FXML
    private void initialize() {

        this.field.setHgap(5);
        this.field.setVgap(5);

        // Add columns
        for (int i = 0; i < gameBoardController.getGameboard().getWidth(); i++) {
            this.field.getColumnConstraints().add(i,
                    new ColumnConstraints(RECT_WIDTH));
        }

        // Add rows
        for (int j = 0; j < gameBoardController.getGameboard().getHeight(); j++) {
            this.field.getRowConstraints().add(j,
                    new RowConstraints(RECT_HEIGHT));
        }


        try {
            // start music
            String musicFile = "tetris.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e){
            // Do nothing
        }



        // start game
        this.gameBoardController.getGameboard().addObserver(this);
        this.gameBoardController.getGameboard().createTetromino();
        this.gameBoardController.start();

        // fps counter
        fpsCounter.setText("[FPS: ??.???]");
        AnimationTimer frameRateMeter = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
                    fpsCounter.setText(String.format("[FPS: %.3f]", frameRate));
                }
            }
        };
        frameRateMeter.start();

        this.initializeField();

        // align and initialize score counter label
        this.scoreArea.setSpacing(5);
        this.scoreCountLabel.setText(Integer.toString(0));
        this.levelCountLabel.setText(Integer.toString(1));
        scoreArea.setAlignment(Pos.BOTTOM_CENTER);
        scoreArea.setPadding(new Insets(0, 0, 0, -field.getMaxWidth()));





    }

    /**
     * Draw the whole gamefield including empty cells.
     * Only run once.
     */
    public void initializeField() {
        // Draw the initial gameboard, initially only contains empty (black) cells
        for (int i = 0; i < this.gameBoardController.getGameboard().getWidth(); i++) {
            for (int j = 0; j < this.gameBoardController.getGameboard().getHeight(); j ++) {
                Cell cell = this.gameBoardController.getGameboard().getCell(i, j);
                Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);
                rect.setFill(Color.web("111111"));
                this.field.add(rect, i, j);
            }
        }
        field.setMaxWidth(RECT_WIDTH*this.gameBoardController.getGameboard().getWidth());
        field.setMaxHeight(RECT_HEIGHT*this.gameBoardController.getGameboard().getHeight());
    }

    /**
     * Helper function: return a Rectangle object from the game field
     */
    private Rectangle getNode(int x, int y) {
        for (Node node : field.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (Rectangle) node;
            }
        }
        return null;
    }

    /**
     * Redraws the game field.
     */
    public void updateField() {
        // Loop over all nodes in GridPane
        for (Node node : field.getChildren()) {
            Rectangle rect = (Rectangle) node;
            // Look for a corresponding Cell object in Gameboard
            Cell cell = this.gameBoardController.getGameboard().getCell(
                    GridPane.getColumnIndex(node),
                    GridPane.getRowIndex(node)
            );
            // If such a cell exists, get the color from that cell. Otherwise paint it black.
            if (cell != null) {
                rect.setFill(Color.web(cell.getColor()));
            } else {
                rect.setFill(Color.web("111111"));
            }
        }

        // Draw active tetronimo
        if (this.gameBoardController.getTetrominoCells() != null) {
            for (Map.Entry<Coordinate, Cell> entry : this.gameBoardController.getTetrominoCells().entrySet()) {
                Rectangle rect = getNode(entry.getKey().getXPos(), entry.getKey().getYPos());

                if (rect != null)
                    rect.setFill(Color.web(entry.getValue().getColor()));
            }
        }

        // Update draw counter
        this.redraws++;
        this.redrawCounter.setText(" [Redraws: " + this.redraws + "]");

        // Update score counter
        this.scoreCountLabel.setText(String.format("%d", this.gameBoardController.getScore()));
        this.levelCountLabel.setText(String.format("%d", this.gameBoardController.getLevel()));

    }

    public void postScore(String name, int score){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("score", score);

        System.out.println(jsonObject.toString());
        HttpURLConnectionInstance httpURLConnectionTest = new HttpURLConnectionInstance();

        try {
            httpURLConnectionTest.sendPOST(jsonObject);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server error");
            alert.setHeaderText(null);
            alert.setContentText("Oh no! Server is not online or could not be reached. Highscore not saved.");
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("You got an exception.");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable obj, Object arg) {
        this.changeEvents++;
        this.changeCounter.setText(" [Change events: " + this.changeEvents + "]");
        System.out.println("[DEBUG] Change detected. Changes: " + this.changeEvents);
        if(gameBoardController.getLost()){ // Check if we have lost after the a new tetromino has been created.
            System.out.println("you lost!!!!!");
            int playerScore = gameBoardController.getScore();
            gameBoardController.resetGameBoardController();
            try {
                mediaPlayer.stop();
            } catch (Exception e){
                // Do nothing
            }
            String defaultName = "SuperMonster253";
            TextInputDialog dialog = new TextInputDialog(defaultName);
            dialog.setTitle("Your weakness disgusts me!");
            dialog.setHeaderText("You lost.");
            dialog.setContentText("Please enter your name:");
            Optional<String> playerName = dialog.showAndWait();

            if (playerName.isPresent()){
                postScore(playerName.get(), playerScore);
            }

            Stage s = Main.getPrimaryStage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_view.fxml"));
            loader.setController(new MenuView(this.gameBoardController));
            try {
                s.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateField();

    }
}
