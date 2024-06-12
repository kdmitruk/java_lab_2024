package pl.umcs.oop.breakout;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;
    public void draw(){
        gc.setFill(Color.BLACK);
        //GraphicsItem.setCanvasSize(640,700);

        gc.fillRect(0, 0, 640, 700);
    }

    public void initialize(){
        gc = this.getGraphicsContext2D();
        draw();
    }

    public GameCanvas() {
        super(640, 700);
    }
}
