public class Main {
    public static void main(String[] args) {
        DeathCauseStatistics deathCauseStatistics = DeathCauseStatistics.fromCsvLine("A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-");
        System.out.println(deathCauseStatistics);
    }
}