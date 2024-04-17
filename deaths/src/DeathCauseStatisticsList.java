import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DeathCauseStatisticsList {

    List<DeathCauseStatistics> stats;

    public void repopulate(Path path){
        try(
            Stream<String> fileLines = Files.lines(path);
        ) {
            stats = fileLines.skip(2)
                    .map(DeathCauseStatistics::fromCsvLine)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeathCauseStatistics> getStats() {
        return stats;
    }
}
