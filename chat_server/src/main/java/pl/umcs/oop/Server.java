package pl.umcs.oop;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket ss;

    public Server() throws IOException {
        ss = new ServerSocket(2137);
    }

    public void listen() throws IOException{
        while (true){
            Socket socket = ss.accept();
            Client client = new Client(this, socket);
            new Thread(client).start();
        }
    }
}
