import java.util.Locale;

public class Ellipse extends Shape{
    private Point center;
    private double rx, ry;
    @Override
    public Point getBound() {
        return new Point(center.x + rx + style.strokeWidth, center.y + ry + style.strokeWidth);
    }

    @Override
    public String toSvg() {
        return String.format(Locale.ENGLISH,"<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\"\n" +
                "  style=\"%s\" />",rx,ry,center.x,center.y, style.toSvg());
    }

    public Ellipse(Style style, Point center, double rx, double ry) {
        super(style);
        this.center = center;
        this.rx = rx;
        this.ry = ry;
    }
}
