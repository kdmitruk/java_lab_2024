package pl.umcs.oop.music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {
    //Playlist playlist = new Playlist();
    @Test
    public void testEmptyPlaylist() {
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }

    @Test
    public void testSingleElement() {
        Playlist playlist = new Playlist();
        playlist.add(new Song("Atr", "OOO", 180));
        assertEquals(1, playlist.size());
    }

    //ten sam obiekt - ta sama przestrzeń w pamięci (podobnie do softlinku)
    //taki sam - takie same atrybuty, inna przestrzeń (podobnie do kopii)
    // Zad 1d
    @Test
    public void testSameElement() {
        Playlist playlist = new Playlist();
        Song newSong = new Song("Atr", "OOO", 180);
        playlist.add(newSong);
        assertEquals(newSong, playlist.getFirst());
    }

    // Zad 1e
    @Test
    public void testEqualElement() {
        Playlist playlist = new Playlist();
        Song newSong = new Song("Atr", "OOO", 180);
        playlist.add(newSong);
        Song testSong = new Song("Atr", "OOO", 180);
        assertEquals(testSong, newSong);
    }

    @Test
    public void testAtSecond() {
        Playlist playlist = new Playlist();
        Song newSong1 = new Song("Atr1", "OO1", 100);
        Song newSong2 = new Song("Atr2", "OO2", 150);
        Song newSong3 = new Song("Atr3", "OO3", 200);
        playlist.add(newSong1);
        playlist.add(newSong2);
        playlist.add(newSong3);
        int whichSecond = 250;
        assertEquals(newSong3, playlist.atSecond(whichSecond));
    }

    private String doesThrowExceptionCommon(int seconds){
        Playlist playlist = new Playlist();
        Song newSong1 = new Song("Atr1", "OO1", 100);
        Song newSong2 = new Song("Atr2", "OO2", 150);
        Song newSong3 = new Song("Atr3", "OO3", 200);
        playlist.add(newSong1);
        playlist.add(newSong2);
        playlist.add(newSong3);
        IndexOutOfBoundsException indexOutOfBoundsException = assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(seconds));
        return indexOutOfBoundsException.getMessage();
    }

    @Test
    void doesThrowException(){
        String result = doesThrowExceptionCommon(60000);
        assertEquals("Too big.", result);
    }

    @Test
    void doesThrowExceptionNegative(){
        String result = doesThrowExceptionCommon(-3);
        assertEquals("Too small.", result);
    }
}
