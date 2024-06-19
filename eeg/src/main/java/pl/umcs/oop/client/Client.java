package pl.umcs.oop.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",2137);
            PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
//            BufferedReader reader = new BufferedReader(new FileReader("/tmp/data.txt"));
            Files.lines(Path.of("tm00.csv")).forEach(line -> {
                printWriter.println(line);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("LINE");
            });
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
