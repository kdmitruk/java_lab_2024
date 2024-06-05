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
    private Client client;

    @FXML
    private void send() {
        System.out.println("SEND");
        String message = textField.getText();
        textField.clear();
        client.send("/broadcast " + message);
    }
    public void receive(String message) {
        outputArea.appendText(message + "\n");
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void bindWithClient(Client client) {
        this.client = client;
        client.setController(this);
    }
}