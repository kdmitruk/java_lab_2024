public class TransformationDecorator extends ShapeDecorator {

    public static class Builder {

        String transform;

        public Builder() {
            this.transform = "";
        }

        public Builder translate(Vec2 translation) {
            this.transform += String.format(" translate(%f %f)", translation.x, translation.y);
            return this;
        }

        public Builder rotate(float angle, Vec2 middle) {
            this.transform += String.format(" rotate(%f %f %f)", angle, middle.x, middle.y);
            return this;
        }

        public Builder scale(Vec2 scaleFactor) {
            this.transform += String.format(" scale(%f %f)", scaleFactor.x, scaleFactor.y);
            return this;
        }

        public TransformationDecorator build(Shape shape) {
            return new TransformationDecorator(shape, transform);
        }
    }

    String transform;

    public TransformationDecorator(Shape shape, String transform) {
        super(shape);
        this.transform = transform;
    }

    @Override
    public String toSvg(String param) {
        return super.toSvg(String.format("%s transform=\"%s\"", param, this.transform));
    }
}
