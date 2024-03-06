import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Scene {
    private ArrayList<Polygon> shapes = new ArrayList<>();
    public void add(Polygon polygon)
    {
        shapes.add(polygon);
    }
    public Point getBounds() {
        double x = 0, y = 0;
        for (Point p : shapes
                .stream()
                .map(Polygon::getBound)
                .toList()) {
            x = Math.max(x, p.x);
            y = Math.max(y, p.y);
        }
        return new Point(x, y);
    }
    public void save(String path)
    {
        try {
            FileWriter fileWriter = new FileWriter(path);
            Point bounds = getBounds();
            fileWriter.write("<HTML>");
            fileWriter.write("<body>");
            fileWriter.write(
                    String.format(
                            "<svg width=\"%f\" height=\"%f\">\n",
                            bounds.x,
                            bounds.y
                    )
            );
            for(Polygon polygon : shapes)
                fileWriter.write("\t" + polygon.toSvg() + "\n");
            fileWriter.write("</svg>");
            fileWriter.write("</body>");
            fileWriter.write("</HTML>");
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("can't write to "+ path);
        }
    }

}
