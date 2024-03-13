//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*SolidFilledPolygon poly = new SolidFilledPolygon(new Vec2[]{
                new Vec2(30, 70),
                new Vec2(60, 80),
                new Vec2(50, 40)
        },"green");
//        Polygon polygon = new Polygon(new Vec2[]{
//                new Vec2(500, 100),
//                new Vec2(220, 20),
//                new Vec2(400, 25),
//                new Vec2(70, 33),
//        });
//        System.out.println(polygon.toSvg());
//        System.out.println(poly.toSvg());
        Scene scene = new Scene();
//        Ellipse ellipse = new Ellipse(new Vec2(100, 200), 50.5, 75.7);
        scene.add(poly);
//        scene.add(poly);
//        scene.add(ellipse);

        scene.save("/tmp/out.html");

         */
        Shape polygon = new Polygon(new Vec2[]{
                new Vec2(500, 100),
                new Vec2(220, 20),
                new Vec2(400, 25),
                new Vec2(70, 33),
        });
        //polygon = new SolidFillShapeDecorator(polygon, "red");

        Shape ellipse = new Ellipse(new Vec2(100, 200), 50.5, 75.7);
        ellipse = new SolidFillShapeDecorator(ellipse,"blue");

        ellipse = new TransformationDecorator.Builder()
                .rotate(1, new Vec2(0, 0))
                .build(ellipse);

        ellipse = new DropShadowDecorator(ellipse);

        polygon = new GradientFillShapeDecorator.Builder()
                .setShape(polygon)
                .addStop(0, "red")
                .addStop(0.3, "white")
                .addStop(1, "cyan")
                .build();

        TransformationDecorator.Builder builder = new TransformationDecorator.Builder();
        Shape s = builder
                .rotate(-50, new Vec2(0, 0))
                .translate(new Vec2(50, 60))
                .scale(new Vec2(1, 1.5))
                .build(new Ellipse(new Vec2(40, 50), 20, 40));

        Scene scene = Scene.getInstance();
        scene.add(polygon);
        scene.add(ellipse);
        scene.add(s);

        scene.save("/tmp/out.html");



    }
}