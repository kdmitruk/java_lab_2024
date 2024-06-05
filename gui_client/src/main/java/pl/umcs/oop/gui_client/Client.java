package pl.umcs.oop.gui_client;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private HelloController controller;

    public Client() throws IOException {
        this.socket = new Socket("localhost", 2137);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine())!= null) {
                System.out.println(message);
                parse(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parse(String message) {
        String[] parts = message.split(" ",2);
        if(parts[0].equals("/broadcast")) {
            controller.receive(parts[1]);
        }
    }

    public void send(String message) {
        this.writer.println(message);
    }

    public void setController(HelloController controller) {
        this.controller = controller;
    }
}
