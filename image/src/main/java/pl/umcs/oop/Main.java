package pl.umcs.oop;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ImageProcessor imageProcessor = new ImageProcessor();

        try {
            imageProcessor.readImage("/tmp/pobrane.jpeg");
            imageProcessor.writeImage("obrazek.jpeg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}