import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class DeathCauseStatistics {

    private String icd10;
    private int totalDeaths;
    private int[] stats;

    public static DeathCauseStatistics fromCsvLine(String line) {
        Function<String, Integer> parseIntFromCsv = s -> s.equals("-") ? 0 : Integer.parseInt(s);
        DeathCauseStatistics result = new DeathCauseStatistics();
        String[] split = line.split("[ \t]+");
        result.icd10 = split[0];
        result.stats = new int[20];
        String[] stats = split[1].split(",");
        result.totalDeaths = parseIntFromCsv.apply(stats[1]);
        for (int i = 0; i < 20; i++)
            result.stats[i] = parseIntFromCsv.apply(stats[i + 2]);
        return result;
    }

    @Override
    public String toString() {
        return "DeathCauseStatistics{" +
                "icd10='" + icd10 + '\'' +
                ", totalDeaths=" + totalDeaths +
                ", stats=" + Arrays.toString(stats) +
                '}';
    }
}
