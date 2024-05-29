package pl.umcs.oop;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        new Thread(client).start();
        while(true){
            client.send(reader.readLine());
        }

    }
}