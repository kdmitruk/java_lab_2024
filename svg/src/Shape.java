abstract public class Shape {
     final protected Style style;


    public Shape(Style style) {
        this.style = style;
    }
    public Shape(){
        this.style = new Style();

    }
    abstract public Point getBound();
    abstract public String toSvg();

}
