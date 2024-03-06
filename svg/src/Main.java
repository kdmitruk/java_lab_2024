//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
      /*   Point point;
         point=new Point(20, 50);
//         System.out.println(point);

         Segment seg= new Segment(point, new Point(60, 40));

//        System.out.println(seg.length());
        System.out.println(seg.toSvg());

        Segment[] p_seg = Segment.perpendicularTo(seg, point);
        System.out.println(p_seg[0].toSvg());
        System.out.println(p_seg[1].toSvg()); */
        Polygon poly = new Polygon(new Point[]{
                new Point (30, 70),
                new Point (60, 80),
                new Point (50, 40)
        });
        Style style= new Style("pink","black",6.0);
        Polygon polygon= new Polygon(new Point[]{
                new Point (500, 100),
                new Point (220, 20),
                new Point (400, 25),
                new Point (70, 33),
        },style);
        System.out.println(polygon.toSvg());
        System.out.println(poly.toSvg());

    }
}