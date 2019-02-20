package org.dat055;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dat055.views.MenuView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String TITLE = "Tetris";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/menu_view.fxml"));
        MenuView menuController = new MenuView();
        loader.setController(menuController);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
