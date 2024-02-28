//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
         Point point;
         point=new Point(20, 50);
//         System.out.println(point);

         Segment seg= new Segment(point, new Point(60, 40));

//        System.out.println(seg.length());
        System.out.println(seg.toSvg());

        Segment[] p_seg = Segment.perpendicularTo(seg, point);
        System.out.println(p_seg[0].toSvg());
        System.out.println(p_seg[1].toSvg());

    }
}