public class Vec2 {
    public final double x,y;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
