package pl.umcs.oop;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    private Server server;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(Server server, Socket socket) throws IOException {
        this.server = server;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine())!= null) {
                server.broadcast(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void send(String message) {
        this.writer.println(message);
    }
}
