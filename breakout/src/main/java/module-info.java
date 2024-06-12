module pl.umcs.oop.breakout {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens pl.umcs.oop.breakout to javafx.fxml;
    exports pl.umcs.oop.breakout;
}