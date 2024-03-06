import java.util.Locale;

public class Polygon {
    private Point[] points;
    private Style style;

    public Polygon(Point[] points) {
        this.points = points;
        this.style = new Style();
 }
    public Polygon(Point[] points, Style style) {
        this.points = points;
        this.style = style;
    }

    public Point getBound() {
        double x = 0, y = 0;
        for (int i = 0; i < points.length; i++) {
            x = Math.max(x, points[i].x);
            y = Math.max(y, points[i].y);
        }
        return new Point(x, y);
    }

    public String toSvg (){
        String result = "";
        for(int i=0; i<this.points.length; i++)
        {
            result += String.format(Locale.ENGLISH, "%f,%f " , points[i].x, points[i].y );
        }
        return String.format(Locale.ENGLISH,
                "<polygon points=\"%s\" style=\"%s />", result,style.toSvg());
    }
}
