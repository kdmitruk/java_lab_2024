package pl.umcs.oop.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem{
    Point2D moveVector = new Point2D(1, -1).normalize();

    public Ball() {
        width = 0.015 * canvasHeight;
        height = 0.015 * canvasHeight;
        x = 0.5 * canvasWidth;
        y = 0.88 * canvasHeight;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void updatePosition(long diff) {
        x = x + moveVector.getX() * diff;
        y = y + moveVector.getY() * diff;
    }
}
