public class DropShadowDecorator extends ShapeDecorator{

    private int index;
    public DropShadowDecorator(Shape decoratedShape) {
        super(decoratedShape);

        index = Scene.getInstance().addFilter(
                "\t<filter id=\"f%d\" x=\"-100%%\" y=\"-100%%\" width=\"300%%\" height=\"300%%\">\n" +
                "\t\t<feOffset result=\"offOut\" in=\"SourceAlpha\" dx=\"5\" dy=\"5\" />\n" +
                "\t\t<feGaussianBlur result=\"blurOut\" in=\"offOut\" stdDeviation=\"5\" />\n" +
                "\t\t<feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\n" +
                "\t</filter>"
        );
    }

    @Override
    public String toSvg(String param) {
        String filterWithId = String.format("filter=\"url(#f%d)\" %s ", index, param);
        return decoratedShape.toSvg(filterWithId);
    }
}
