package pl.umcs.oop.breakout;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class GameCanvas extends Canvas {
    private GraphicsContext gc;
    private Paddle paddle;
    private Ball ball;
    private Boolean running = false;
    private AnimationTimer animationTimer = new AnimationTimer() {
        private long before;

        @Override
        public void start() {
            super.start();
            this.before = System.nanoTime();
        }

        @Override
        public void handle(long now) {

            long diff = (now - before) / 1_000_000;
            before = now;
            ball.updatePosition(diff);
            draw();
        }
    };

    public void draw(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 640, 700);
        paddle.draw(gc);
        ball.draw(gc);
    }

    public void initialize(){
        gc = this.getGraphicsContext2D();
        GraphicsItem.setCanvasSize(getWidth(), getHeight());
        this.paddle = new Paddle();
        ball = new Ball();
        this.setOnMouseMoved(mouseEvent -> {
            paddle.setX(mouseEvent.getX());
            //if(running) {
              //  ball.updatePosition();
           // }
            draw();
        });
        this.setOnMouseClicked(mouseEvent -> {
            running = true;
            animationTimer.start();
        });
        draw();
    }

    public GameCanvas() {
        super(640, 700);
    }
}
