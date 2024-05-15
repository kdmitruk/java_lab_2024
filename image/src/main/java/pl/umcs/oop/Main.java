package pl.umcs.oop;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ImageProcessor imageProcessor = new ImageProcessor();

        try {
            imageProcessor.readImage("/tmp/lake.jpg");
//            imageProcessor.setBrightness(100);
//            imageProcessor.setBrightness(-100);
            {
                long startTime = System.currentTimeMillis();
                imageProcessor.setBrightness(100);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime-startTime);
            }
            {
                long startTime = System.currentTimeMillis();
                imageProcessor.setBrightness2(100);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime-startTime);
            }
            imageProcessor.writeImage("obrazek.jpeg");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}