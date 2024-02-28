import java.util.Locale;

public class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public String toSvg(){
        return String.format(Locale.ENGLISH,"<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:red;stroke-width:2\" />", start.x,start.y, end.x,end.y);
    }
    //<line x1="0" y1="0" x2="300" y2="200" style="stroke:red;stroke-width:2" />


    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
    public double length(){
        return Math.hypot(end.x -start.x, end.y- start.y);
    }

}
