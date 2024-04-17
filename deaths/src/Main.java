import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
//        DeathCauseStatistics deathCauseStatistics = DeathCauseStatistics.fromCsvLine("A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-");
//        System.out.println(deathCauseStatistics.getDeathsForAge(66));

        DeathCauseStatisticsList list = new DeathCauseStatisticsList();
        list.repopulate(Path.of("./assets/zgony.csv"));
        //list.getStats().forEach(System.out::println);

        var codes = new ICDCodeTabularOptimizedForMemory(
                Path.of("./assets/icd10.txt")
        );

        list.mostDeadlyDiseases(90, 1000).forEach(currentStat -> {
            String code = currentStat.getIcd10();
            System.out.println(
                    code + ": " + codes.getDescription(code)
            );
        });

    }
}