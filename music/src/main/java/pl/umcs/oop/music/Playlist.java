package pl.umcs.oop.music;

import java.util.ArrayList;

// Zad 1f
public class Playlist extends ArrayList<Song> {
    public Song atSecond(int seconds){
        int sumOfSeconds = 0;
        for (Song song : this){
            if ((seconds >= sumOfSeconds) && (seconds < (sumOfSeconds + song.getDuration()))) {
                return song;
            }
            sumOfSeconds += song.getDuration();
        }
        return null;
    }
}
