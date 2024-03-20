import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate deathDate;

    private List<Person> parents =  new ArrayList<>();

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
                ", parents=" + parents +
                '}';
    }

    public static List<Person> fromCsv(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        List<Person> people = new ArrayList<>();
        Map<String, PersonWithParentStrings> peopleWithParentStrings = new HashMap<>();

        String line;
        reader.readLine();
        while((line = reader.readLine()) != null){

//            Person person = Person.fromCsvLine(line);
            var personWithParentStrings = PersonWithParentStrings.fromCsvLine(line);
            var person = personWithParentStrings.person;

            try {
                person.validateLifespan();
                person.validateAmbiguity(people);
                people.add(person);

                peopleWithParentStrings.put(person.name, personWithParentStrings);
            }
            catch (NegativeLifespanExeption | AmbiguousPersonException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        PersonWithParentStrings.linkRelatives(peopleWithParentStrings);

        reader.close();
        return people;
    }

    public void addParent(Person parent){
        parents.add(parent);
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
    private void validateAmbiguity(List<Person> people) throws AmbiguousPersonException {
        for(Person person : people){
            if(person.getName().equals(getName())){
                throw new AmbiguousPersonException(person);
            }
        }
    }
}
