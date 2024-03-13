import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene {

    private List<String> defs = new ArrayList<>();
    public static Scene instance;
    private static int index = 0;

    private Scene(){}

    static public Scene getInstance(){
        if(instance == null){
            instance = new Scene();
        }

        return instance;
    }

    public int addFilter(String filter){
        defs.add(
                String.format(filter, ++index)
        );
        return index;
    }

    private ArrayList<Shape> shapes = new ArrayList<>();
    public void add(Shape polygon)
    {
        shapes.add(polygon);
    }
    public Vec2 getBounds() {
        double x = 0, y = 0;
        for (Vec2 p : shapes
                .stream()
                .map(Shape::getBound)
                .toList()) {
            x = Math.max(x, p.x);
            y = Math.max(y, p.y);
        }
        return new Vec2(x, y);
    }
    public void save(String path)
    {
        try {
            FileWriter fileWriter = new FileWriter(path);
            Vec2 bounds = getBounds();
            fileWriter.write("<HTML>");
            fileWriter.write("<body>");
            fileWriter.write(
                    String.format(
                            "<svg width=\"%f\" height=\"%f\">\n",
                            bounds.x,
                            bounds.y
                    )
            );
            fileWriter.write("<defs>");
            /* \t<filter id=\"f%d\" x=\"-100%%\" y=\"-100%%\" width=\"300%%\" height=\"300%%\">\n" +
            "\t\t<feOffset result=\"offOut\" in=\"SourceAlpha\" dx=\"5\" dy=\"5\" />\n" +
            "\t\t<feGaussianBlur result=\"blurOut\" in=\"offOut\" stdDeviation=\"5\" />\n" +
            "\t\t<feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\n" +
            "\t</filter>", index
            */
            for(String def : defs){
                fileWriter.write(def + "\n");
            }
            fileWriter.write("</defs>");
            for(Shape polygon : shapes)
                fileWriter.write("\t" + polygon.toSvg("") + "\n");
            fileWriter.write("</svg>");
            fileWriter.write("</body>");
            fileWriter.write("</HTML>");
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("can't write to "+ path);
        }
    }

}
