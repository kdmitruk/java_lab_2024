package pl.umcs.oop;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

    private ServerSocket ss;
    private List<Client> clients = new ArrayList<>();
    private Map<String, Client> clientMap = new HashMap<>();

    public Server() throws IOException {
        ss = new ServerSocket(2137);
    }

    public List<String> serverUsersLogins(){
        return clientMap.keySet().stream().toList();
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

    public void clientLogged(Client loggedClient) {
        clientMap.put(loggedClient.getLogin(), loggedClient);
        for(Client currentClient : clients) {
            if(currentClient != loggedClient) {
                currentClient.send(String.format("%s zalogował się", loggedClient.getLogin()));
            }
        }
    }

    public void clientLeft(Client leavingClient) {
        clients.remove(leavingClient);
        try {
            clientMap.remove(leavingClient.getLogin());
        } catch (Exception e) {}

        for(Client currentClient : clients) {
            currentClient.send(String.format("%s wylogował się", leavingClient.getLogin()));
        }
    }
}
