package pl.umcs.oop.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem{

    private Point2D moveVector = new Point2D(1,-1).normalize();
    private double velocity = 300;
    double lastX, lastY;

    public Ball() {
        width = height = canvasHeight * 0.01;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x,y, width, height);
    }
    void setPosition(Point2D point){
        this.x= point.getX() - width/2;
        this.y= point.getY() - width/2-5;
    }
    public void updatePosition(double difference) {
        lastX = this.x;
        lastY = this.y;
        this.x += moveVector.getX() * velocity * difference;
        this.y += moveVector.getY() * velocity * difference;
    }

    public void bounceVertically() {
        moveVector = new Point2D(-moveVector.getX(),moveVector.getY()).normalize();

    }

    public void bounceHorizontally() {
        moveVector = new Point2D(moveVector.getX(),-moveVector.getY()).normalize();
    }

    public Point2D[] borderPoints() {
        Point2D[] result = new Point2D[4];
        result[0] = new Point2D(x, y+height/2);
        result[1] = new Point2D(x+width, y+height/2);
        result[2] = new Point2D(x+width/2, y);
        result[3] = new Point2D(x+width/2, y+height);
        return result;
    }

    public void bounceFromPaddle(double pos) {
        moveVector = new Point2D(pos*5, -moveVector.getY()).normalize();
    }
}
