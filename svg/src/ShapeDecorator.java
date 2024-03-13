public class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public Vec2 getBound() {
        return decoratedShape.getBound();
    }

    @Override
    public String toSvg(String param) {
        return decoratedShape.toSvg(param);
    }
}
