import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonWithParentStrings {

    final public Person person;

    final public List<String> parentNames;

    private PersonWithParentStrings(Person person, List<String> parentNames) {
        this.person = person;
        this.parentNames = parentNames;
    }

    static public PersonWithParentStrings fromCsvLine(String line){
        Person person = Person.fromCsvLine(line);
        List<String> parentNames = new ArrayList<>();

        String[] values = line.split(",", -1);

        for(int i = 3; i <= 4; i++){
            if(!values[i].isEmpty())
                parentNames.add(values[i]);
        }

        return new PersonWithParentStrings(person, parentNames);
    }

    static void linkRelatives(Map<String, PersonWithParentStrings> people){
        for(var personWithParentStrings : people.values()){
            for(var parentName : personWithParentStrings.parentNames){
                personWithParentStrings.person.addParent(people.get(parentName).person);
            }
        }
    }

}
