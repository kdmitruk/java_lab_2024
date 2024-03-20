import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate deathDate;

    public Person(String name, LocalDate birthDate, LocalDate deathDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }
    public static Person fromCsvLine(String csvLine){
        String[] parts = csvLine.split(",", -1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //Marek Kowalski,15.05.1899,25.06.1957,,
        LocalDate birthDate = LocalDate.parse(parts[1],formatter);
        LocalDate deathDate = !parts[2].equals("") ? LocalDate.parse(parts[2],formatter) :null;
        return new Person (parts[0],birthDate,deathDate);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                '}';
    }

    public static List<Person> fromCsv(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<Person> people = new ArrayList<>();
        String line;
        reader.readLine();
        while((line = reader.readLine()) != null){
            Person person = Person.fromCsvLine(line);
            try {
                person.validateLifespan();
                people.add(Person.fromCsvLine(line));
            } catch (NegativeLifespanExeption e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
        reader.close();
        return people;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    private void validateLifespan() throws NegativeLifespanExeption {
        if(deathDate != null && deathDate.isBefore(birthDate)){
            throw new NegativeLifespanExeption(this);
        }
    }
}
