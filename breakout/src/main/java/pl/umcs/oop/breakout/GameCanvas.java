package pl.umcs.oop.breakout;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();
    private Paddle paddle;
    private Ball ball;
    private Scene scene;
    private Boolean gameRunning = false;
    private List<Brick> bricks;

    private AnimationTimer animationTimer= new AnimationTimer(){
        private long lastUpdate;
        @Override
        public void handle(long now){
            double diff = (now - lastUpdate)/1_000_000_000.;
            ball.updatePosition(diff);
            draw();
            lastUpdate = now;
            if(shouldBallBounceHorizontally()){
                ball.bounceHorizontally();
            }
            if(shouldBallBounceVertically()){
                ball.bounceVertically();
            }
            if(shouldBallBounceFromPaddle())
                ball.bounceFromPaddle((-paddle.getPosition()+(ball.x+ball.width/2))/paddle.width);
            for(var brick : bricks) {
                Point2D[] borderPoints = ball.borderPoints();
                Brick.CrushType crushType = brick.crushes(borderPoints[0], borderPoints[1], borderPoints[2], borderPoints[3]);
                if(crushType != Brick.CrushType.NoCrush) {
                    if(crushType == Brick.CrushType.HorizontalCrush)
                        ball.bounceVertically();
                    else
                        ball.bounceHorizontally();
                    bricks.remove(brick);
                    break;
                }
            }
        }

        @Override
        public void start(){
            super.start();
            lastUpdate = System.nanoTime();
        }
    };

    public GameCanvas() {
        super(640, 800);
        GraphicsItem.setCanvasSize(getWidth(), getHeight());
        scene = getScene();

        this.setOnMouseMoved(mouseEvent -> {
            paddle.setPosition(mouseEvent.getX());
            draw();
            if(!gameRunning) {
                ball.setPosition(new Point2D(mouseEvent.getX(), paddle.getY()));
            }

        });
        this.setOnMouseClicked(mouseEvent -> {
            gameRunning =true;
            animationTimer.start();
        });

    }

    public void draw(){
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,getWidth(), getHeight());
        paddle.draw(graphicsContext);
        ball.draw(graphicsContext);
        bricks.forEach(brick -> brick.draw(graphicsContext));

    }
    public void initialize(){
        paddle = new Paddle();
        ball = new Ball();
        loadLevel();
    }

    private boolean shouldBallBounceHorizontally() {
        return ball.lastY > 0 && ball.y <= 0;
    }

    private boolean shouldBallBounceFromPaddle() {
        return ball.lastY+ ball.height < paddle.y && ball.y+ ball.height >= paddle.y
                && ball.x >=paddle.x && ball.x <= paddle.x+paddle.width;
    }

    private boolean shouldBallBounceVertically() {
        return  (ball.x <= 0 && ball.lastX > 0)
                || (ball.x+ball.width >= getWidth() - 1 && ball.lastX+ball.width < getWidth() - 1);
    }

    public void loadLevel(){
        bricks = new ArrayList<>();
        Color colors[] = new Color[]{Color.RED, Color.BEIGE, Color.BROWN, Color.GREENYELLOW, Color.BLUE};
        Brick.setGridRows(20,10);
        for(int i=0;i<5;i++){
            for(int j=0;j<Brick.getGridColumns();j++)
                bricks.add(new Brick(j,i+2,colors[i]));
        }
    }



}
