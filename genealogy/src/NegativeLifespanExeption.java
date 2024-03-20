public class NegativeLifespanExeption extends Exception{
    public NegativeLifespanExeption(Person person) {
        super(String.format("%s, urodził(a) się, %s, później niż umarła, %s", person.getName(), person.getBirthDate(), person.getDeathDate()));
    }
}
