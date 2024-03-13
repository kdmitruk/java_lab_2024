import java.util.Locale;

public class Polygon implements Shape{
    private Vec2[] points;


    public Polygon(Vec2[] points) {
        this.points = points;
    }


    @Override
    public Vec2 getBound() {
        double x = 0, y = 0;
        for (int i = 0; i < points.length; i++) {
            x = Math.max(x, points[i].x);
            y = Math.max(y, points[i].y);
        }
        return new Vec2(x, y);
    }



    public String toSvg (String parameters){
        String result = "";
        for(int i=0; i<this.points.length; i++)
        {
            result += String.format(Locale.ENGLISH, "%f,%f " , points[i].x, points[i].y );
        }
        return String.format(Locale.ENGLISH,
                "<polygon points=\"%s\" %s/>", result,parameters);
    }
}
