package pl.umcs.oop.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicsItem {
    private static int gridRows;
    private static int gridColumns;
    private Color color;

    public Brick(int gridX, int gridY, Color color) {
        this.color = color;
        width = canvasWidth/gridColumns;
        height = canvasHeight/gridRows;
        x=gridX * width;
        y=gridY * height;
    }
    public enum CrushType {NoCrush, HorizontalCrush, VerticalCrush};

    public static int getGridRows() {
        return gridRows;
    }

    public static int getGridColumns() {
        return gridColumns;
    }

    public static void setGridRows(int gridRows, int gridColumns) {
        Brick.gridRows = gridRows;
        Brick.gridColumns = gridColumns;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x,y,width,height);
        graphicsContext.setStroke(color.brighter());
        graphicsContext.strokeLine(x,y, x+width, y);
        graphicsContext.strokeLine(x,y, x, y+height);
        graphicsContext.setStroke(color.darker());
        graphicsContext.strokeLine(x,y+height, x+width, y+height);
        graphicsContext.strokeLine(x+width,y, x+width, y+height);
    }

    CrushType crushes(Point2D left, Point2D right, Point2D top, Point2D bottom) {
        if(contains(left) || contains(right)) return CrushType.HorizontalCrush;
        if(contains(top) || contains(bottom)) return CrushType.VerticalCrush;
        return CrushType.NoCrush;
    }

    boolean contains(Point2D point) {
        return x <= point.getX() && point.getX() <= x+width && y <= point.getY() && point.getY() <= y+height;
    }
}
