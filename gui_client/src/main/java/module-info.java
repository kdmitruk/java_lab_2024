module pl.umcs.oop.gui_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.umcs.oop.gui_client to javafx.fxml;
    exports pl.umcs.oop.gui_client;
}