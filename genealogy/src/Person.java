import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Person {
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate deathDate;

    private List<Person> parents = new ArrayList<>();

    public Person(String name, LocalDate birthDate, LocalDate deathDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public static Person fromCsvLine(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //Marek Kowalski,15.05.1899,25.06.1957,,
        LocalDate birthDate = LocalDate.parse(parts[1], formatter);
        LocalDate deathDate = !parts[2].equals("") ? LocalDate.parse(parts[2], formatter) : null;
        return new Person(parts[0], birthDate, deathDate);
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", birthDate=" + birthDate + ", deathDate=" + deathDate + ", parents=" + parents + '}';
    }

    public static List<Person> fromCsv(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        List<Person> people = new ArrayList<>();
        Map<String, PersonWithParentStrings> peopleWithParentStrings = new HashMap<>();

        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {

//            Person person = Person.fromCsvLine(line);
            var personWithParentStrings = PersonWithParentStrings.fromCsvLine(line);
            var person = personWithParentStrings.person;

            try {
                person.validateLifespan();
                person.validateAmbiguity(people);
                people.add(person);

                peopleWithParentStrings.put(person.name, personWithParentStrings);
            } catch (NegativeLifespanExeption | AmbiguousPersonException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        PersonWithParentStrings.linkRelatives(peopleWithParentStrings);

        reader.close();
        return people;
    }

    public void addParent(Person parent) {
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
        if (deathDate != null && deathDate.isBefore(birthDate)) {
            throw new NegativeLifespanExeption(this);
        }
    }

    private void validateAmbiguity(List<Person> people) throws AmbiguousPersonException {
        for (Person person : people) {
            if (person.getName().equals(getName())) {
                throw new AmbiguousPersonException(person);
            }
        }
    }

    //object "Janusz Kowalski" as JanuszKowalski
    public String generateTree() {
        String result = "@startuml\n%s\n%s\n@enduml";
        Function<Person, String> objectName = person -> person.getName().replaceAll(" ", "");
        Function<Person, String> objectLine = person -> String.format("object \"%s\" as %s", person.getName(), objectName.apply(person));
        //result=String.format(result,objectLine.apply(this));
        StringBuilder objects = new StringBuilder();
        StringBuilder relations = new StringBuilder();
        objects.append(objectLine.apply(this)).append("\n");
        parents.forEach(parent -> {
            objects.append(objectLine.apply(parent)).append("\n");
            relations.append(String.format("%s <-- %s\n", objectName.apply(parent), objectName.apply(this)));
        });
        result = String.format(result, objects, relations);
        return result;
    }


    public static String generateTree(List<Person> people, Predicate<Person> condition, Function<String, String> postProcess) {
        String result = "@startuml\n%s\n%s\n@enduml";
        Function<String, String> objectName = str -> str.replaceAll("\\s+", "");
        Function<String, String> objectLine = str -> String.format("object \"%s\" as %s",str, objectName.apply(str));
        Function<String, String> objectLineAndPostprocess = objectLine.andThen(postProcess);

        Map<Boolean, List<Person>> groupedPeople = people.stream()
                .collect(Collectors.partitioningBy(condition));

        Set<String> objects = groupedPeople.get(true).stream()
                .map(person -> person.name)
                .map(objectLineAndPostprocess)
                .collect(Collectors.toSet());
        objects.addAll(groupedPeople.get(false).stream()
                .map(person -> person.name)
                .map(objectLine)
                .collect(Collectors.toSet())
        );

        Set<String> relations = people.stream()
                .flatMap(person -> person.parents.stream()
                        .map(parent -> objectName.apply(parent.name) + "<--" + objectName.apply(person.name)))
                .collect(Collectors.toSet());

        String objectString = String.join("\n", objects);
        String relationString = String.join("\n", relations);

        return String.format(result, objectString, relationString);
    }

    public static List<Person> filterByName(List<Person> people, String text){
        return people.stream()
                .filter(person -> person.getName().contains(text))
                .collect(Collectors.toList());
    }
    public static List<Person> sortedByBirth(List<Person> people){
        return people.stream()
                .sorted(Comparator.comparing(Person::getBirthDate))
                .collect(Collectors.toList());
    }

    public static List<Person> sortByLifespan(List<Person> people){

        Function<Person, Long> getLifespan = person
                -> person.deathDate.toEpochDay() - person.birthDate.toEpochDay();

        return people.stream()
                .filter(person -> person.deathDate != null)
                .sorted((o2, o1) -> Long.compare(getLifespan.apply(o1), getLifespan.apply(o2)))
//                .sorted(Comparator.comparingLong(getLifespan::apply))
//                .sorted(Collections.reverseOrder())
                .toList();
    }

    public static Person findOldestLiving(List<Person> people){
        return people.stream()
                .filter(person -> person.deathDate == null)
                .min(Comparator.comparing(Person::getBirthDate))
                .orElse(null);

    }
}
