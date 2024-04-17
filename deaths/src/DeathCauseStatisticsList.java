import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
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

    public List<DeathCauseStatistics> mostDeadlyDiseases(int age, int n)
    {
        /*List<DeathCauseStatistics> results = new ArrayList<>();
        for(DeathCauseStatistics currentStat : stats){
            results.add(currentStat.getDeathsForAge(age));
        }*/
        List<DeathCauseStatistics> results = new ArrayList<>(stats);
        results.sort((disease1, disease2)->Integer.compare(
                disease1.getDeathsForAge(age).deathCount(),
                disease2.getDeathsForAge(age).deathCount()
        ));
        return results.reversed().subList(0, n);
    }

    public List<DeathCauseStatistics> getStats() {
        return stats;
    }
}
