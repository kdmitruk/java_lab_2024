package pl.umcs.oop.imageweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RectangleController {

    @GetMapping("hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("rect")
    public Rectangle rect() {
        return new Rectangle(4, 2, 6, 9, "red");
    }

}
