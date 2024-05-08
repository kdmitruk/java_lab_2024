package pl.umcs.oop.music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaylistTest {
    //Playlist playlist = new Playlist();
    @Test
    public void testEmptyPlaylist(){
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }
    @Test
    public void testSingleElement(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Atr","OOO",180));
        assertEquals(1,playlist.size());
    }
}
