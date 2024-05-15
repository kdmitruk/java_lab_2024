package pl.umcs.oop;

import java.awt.image.BufferedImage;

import static java.lang.Math.clamp;

public class SetBrightnessWorker implements Runnable{
    private int begin,end;
    private int brightness;
    private BufferedImage image;

    public SetBrightnessWorker(int begin, int end, int brightness, BufferedImage image) {
        this.begin = begin;
        this.end = end;
        this.brightness = brightness;
        this.image = image;
    }

    @Override
    public void run() {
        for(int y=begin;y<end;y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x,y);
                int b=rgb&0xFF;
                int g=(rgb&0xFF00)>>8;
                int r=(rgb&0xFF0000)>>16;
                b=clamp(b+brightness,0,255);
                g=clamp(g+brightness,0,255);
                r=clamp(r+brightness,0,255);
                rgb=(r<<16)+(g<<8)+b;
                image.setRGB(x,y,rgb);
            }
        }
    }
}
