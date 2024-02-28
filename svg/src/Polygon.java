import java.util.Locale;

public class Polygon {
    private Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }
    public String toSvg (){
        String result = "";
        for(int i=0; i<this.points.length; i++)
        {
            result += String.format(Locale.ENGLISH, "%f,%f " , points[i].x, points[i].y );
        }
        return String.format(Locale.ENGLISH,"<polygon points=\"%s\" style=\"fill:lime;stroke:purple;stroke-width:3\" />", result);
    }
}
//<polygon points="200,10 250,190 150,190" style="fill:lime;stroke:purple;stroke-width:3" />
