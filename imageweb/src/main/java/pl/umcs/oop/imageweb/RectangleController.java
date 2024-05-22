package pl.umcs.oop.imageweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RectangleController {
    private List<Rectangle> rectangles = new ArrayList<>();
    @GetMapping("hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("rect")
    public List<Rectangle> rect() {
        return rectangles;
    }
    @GetMapping("addrect")
    public void addrect(){
        rectangles.add(new Rectangle(5, 8, 3, 7, "green" ));
    }
    @GetMapping("tosvg")
    public String tosvg(){
        StringBuilder result = new StringBuilder();
        result.append("<svg width=\"1000\" height=\"1000\">");
        for (Rectangle rect : rectangles){
            result.append(String.format("<rect width=\"%d\" height=\"%d\" x=\"%d\" y=\"%d\" fill=\"%s\"/>", rect.w(), rect.h(), rect.x(), rect.y(), rect.color()));
        }
        result.append("</svg>");
        return result.toString();
    }

    @PostMapping("rect")
    public void postrect(@RequestBody Rectangle rectangle){
        rectangles.add(rectangle);
    }
    // curl -X POST -H 'Content-Type:application/json' -d '{"x": 50, "y": 70, "w": 30, "h": 60, "color": "blue"}' localhost:8080/rect



}
