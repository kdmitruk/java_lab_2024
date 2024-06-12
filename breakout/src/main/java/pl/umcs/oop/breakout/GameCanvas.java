package pl.umcs.oop.breakout;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;
    private Paddle paddle;
    public void draw(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 640, 700);
        paddle.draw(gc);
    }

    public void initialize(){
        gc = this.getGraphicsContext2D();
        GraphicsItem.setCanvasSize(getWidth(), getHeight());
        this.paddle = new Paddle();
        this.setOnMouseMoved(mouseEvent -> {
            paddle.setX(mouseEvent.getX());
            draw();
        });
        draw();
    }

    public GameCanvas() {
        super(640, 700);
    }
}
