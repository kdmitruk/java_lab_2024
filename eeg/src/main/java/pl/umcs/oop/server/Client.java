package pl.umcs.oop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable{
    private List<List<Float>> data = new ArrayList<>();
    private BufferedReader reader;

    public Client(Socket socket) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void parseMessage(String message)
    {
        System.out.println(message);
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine())!= null) {
                parseMessage(message);
            }
            //this.leave();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
