public class SolidFilledPolygon extends Polygon {
    private String fillColour;

    @Override
    public String toSvg(String parameters) {
        String f=String.format("fill=\"%s\" %s ",fillColour,parameters);
        return super.toSvg(f);
    }

    public SolidFilledPolygon(Vec2[] points, String colour){
        super(points);
        fillColour=colour;
    }
}
