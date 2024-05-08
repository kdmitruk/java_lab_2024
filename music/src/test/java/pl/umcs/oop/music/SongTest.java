package pl.umcs.oop.music;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.umcs.oop.database.DatabaseConnection;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    @BeforeAll
    static void openDatabase(){
        DatabaseConnection.connect("songs.db","songs");
    }
    @Test
    void readSingle() throws SQLException {
        Optional<Song> testSong = Song.Persistence.read(5);
        Song expectedSong = new Song("Queen","Bohemian Rhapsody",355);
        assertTrue(testSong.isPresent());
        assertEquals(expectedSong,testSong.get());
    }
    @Test
    void readSingleWrongID() throws SQLException {
        Optional<Song> testSong = Song.Persistence.read(53);
        //Song expectedSong = new Song("Queen","Bohemian Rhapsody",355);
        assertFalse(testSong.isPresent());
    }

    @AfterAll
    static void closeDatabase(){
        DatabaseConnection.disconnect("songs");
    }
}
