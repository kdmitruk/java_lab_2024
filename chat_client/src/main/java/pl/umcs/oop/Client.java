package pl.umcs.oop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {
    Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

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
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message) {
        this.writer.println(message);
    }
}
