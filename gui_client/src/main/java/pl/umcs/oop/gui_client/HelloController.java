package pl.umcs.oop.gui_client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class HelloController {
    @FXML
    private TextField textField;
    @FXML
    private TextArea outputArea;


    @FXML
    private void send() {
        System.out.println("SEND");
        String message = textField.getText();
        outputArea.appendText(message + "\n");
        textField.clear();
    }
}