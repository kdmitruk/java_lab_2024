package pl.umcs.oop;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    private Server server;
    private BufferedReader reader;
    private PrintWriter writer;
    private String login;
    private Socket socket;

    public Client(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        String message;
        try {
            this.authenticate();
            while ((message = reader.readLine())!= null) {
                server.broadcast(message);
            }
            this.leave();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void send(String message) {
        this.writer.println(message);
    }

    private void authenticate() throws IOException {
        login = reader.readLine();
        server.clientLogged(this);
    }

    public String getLogin() {
        return login;
    }

    private void leave() throws IOException {
        socket.close();
        server.clientLeft(this);
    }
}
