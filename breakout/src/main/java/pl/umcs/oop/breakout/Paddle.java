package pl.umcs.oop.breakout;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GraphicsItem {
    public Paddle() {
        height=canvasHeight*.02;
        width = canvasWidth * 0.2;

        y=canvasHeight*.9;
        x=(canvasWidth-width)/2;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(x,y,width,height);
    }
    void setPosition(double x){
        this.x = clamp(x-width/2,0,canvasWidth - width);
    }
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public double getPosition() {
        return x+width/2;
    }
}
