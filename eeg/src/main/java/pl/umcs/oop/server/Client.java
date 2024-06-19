package pl.umcs.oop.server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;

public class Client implements Runnable{
    private List<List<Float>> data = new ArrayList<>();
    private BufferedReader reader;


    public Client(Socket socket) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void parseMessage(String message)
    {
        //System.out.println(message);
        List<Float> lineData = Arrays.stream(message.split(",")).map(Float::parseFloat).toList();
        data.add(lineData);

    }

    public void generate(int index) throws IOException {
        List<Float> dataLine = data.get(index);
        BufferedImage image = new BufferedImage(dataLine.size(), 40, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < dataLine.size(); i++) {
            int y0 = image.getHeight() / 2;
            int y = (int) (-dataLine.get(i) + y0);
            image.setRGB(i, y, 0xffff0000);
        }
        //ImageIO.write(image, "png", new File("/tmp/image.png"));
        File file = new File("/tmp/data.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println(encodeBase64(image));
        writer.close();
        System.out.println(encodeBase64(image));
    }

    private static String encodeBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return base64Image;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine())!= null && !message.equals("Bye")) {
                parseMessage(message);
                generate(data.size() - 1);
            }
            //this.leave();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
