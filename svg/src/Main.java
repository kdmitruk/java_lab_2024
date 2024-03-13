//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Polygon poly = new Polygon(new Vec2[]{
                new Vec2(30, 70),
                new Vec2(60, 80),
                new Vec2(50, 40)
        });
        Polygon polygon = new Polygon(new Vec2[]{
                new Vec2(500, 100),
                new Vec2(220, 20),
                new Vec2(400, 25),
                new Vec2(70, 33),
        });
        System.out.println(polygon.toSvg());
        System.out.println(poly.toSvg());
        Scene scene = new Scene();
        Ellipse ellipse = new Ellipse(new Vec2(100, 200), 50.5, 75.7);
        scene.add(polygon);
        scene.add(poly);
        scene.add(ellipse);
        scene.save("/tmp/out.html");
    }
}