public class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

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
