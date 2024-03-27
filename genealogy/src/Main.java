import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        String csvLine = "Marek Kowalski,15.05.1899,25.06.1957,,";
//        Person person = Person.fromCsvLine(csvLine);
//        System.out.println(person);
//        try {
//            List<Person> people = Person.fromCsv("family.csv");
//            for(Person person : people){
//                System.out.println(person);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        PlantUMLRunner.setJarPath("./plantuml-1.2024.3.jar");
        PlantUMLRunner.generate("@startuml\n" +
                "Class11 <|.. Class12\n" +
                "Class13 --> Class14\n" +
                "Class15 ..> Class16\n" +
                "Class17 ..|> Class18\n" +
                "Class19 <--* Class20\n" +
                "@enduml\n",
                "image_output",
                "test"
                );

    }
}