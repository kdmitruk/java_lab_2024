package pl.umcs.oop.breakout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameCanvas gameCanvas = new GameCanvas();
        stage.setTitle("Game");

        GridPane pane = new GridPane();

        pane.add(gameCanvas,0,0);
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        gameCanvas.initialize();
        gameCanvas.draw();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}