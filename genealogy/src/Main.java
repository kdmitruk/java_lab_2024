public class Main {
    public static void main(String[] args) {

        String csvLine = "Marek Kowalski,15.05.1899,25.06.1957,,";
        Person person = Person.fromCsvLine(csvLine);
        System.out.println(person);

    }
}