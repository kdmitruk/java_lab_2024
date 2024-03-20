public class AmbiguousPersonException extends Exception {
    public AmbiguousPersonException(Person person) {
        super(String.format("%s pojawia się w pliku wielokrotnie", person.getName()));
    }
}
