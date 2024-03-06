import java.util.Locale;

public class Style {
    public final String fillColor;
    public final String strokeColor;
    public final double strokeWidth;

    public Style(String fillColor, String strokeColor, double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public Style() {
        this("transparent","black",1.0);
    }

    public String toSvg(){
        return String.format(Locale.ENGLISH,
                "fill:%s;stroke:%s;stroke-width:%f\" />",
                fillColor,strokeColor,strokeWidth);
    }

}
