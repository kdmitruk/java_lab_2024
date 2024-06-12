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
        GameCanvas gc = new GameCanvas();
        gc.initialize();
        GridPane gp = new GridPane();
        gp.add(gc, 0, 0);
        Scene scene = new Scene(gp, 640, 700);
        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}