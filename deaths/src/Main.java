import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
//        DeathCauseStatistics deathCauseStatistics = DeathCauseStatistics.fromCsvLine("A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-");
//        System.out.println(deathCauseStatistics.getDeathsForAge(66));

        DeathCauseStatisticsList list = new DeathCauseStatisticsList();
        list.repopulate(Path.of("./assets/zgony.csv"));
        //list.getStats().forEach(System.out::println);
        list.mostDeadlyDiseases(1, 10).forEach(System.out::println);

    }
}