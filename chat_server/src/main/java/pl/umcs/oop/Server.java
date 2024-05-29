package pl.umcs.oop;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket ss;
    private List<Client> clients = new ArrayList<>();

    public Server() throws IOException {
        ss = new ServerSocket(2137);
    }

    public void listen() throws IOException{
        while (true){
            Socket socket = ss.accept();
            Client client = new Client(this, socket);
            new Thread(client).start();
            clients.add(client);
        }
    }

    public void broadcast(String message) {
        for(Client client : clients) {
            client.send(message);
        }
    }

}
