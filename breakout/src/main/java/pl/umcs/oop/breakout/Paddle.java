package pl.umcs.oop.breakout;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GraphicsItem{
    public Paddle() {
        width = 0.2 * canvasWidth;
        height = 0.02 * canvasHeight;
        y = 0.9 * canvasHeight;
        x = 0.5 * canvasWidth - 0.5 * width;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(x,y,width,height);
    }

    public void setX(double x){
        this.x = x - 0.5 * width;
        if(x < width/2){
            this.x = 0;
        }
        else if (x > canvasWidth - width/2) {
            this.x = canvasWidth - width;
        }
    }
}
