package pl.umcs.oop.auth;

public record Account(int id, String username) {
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
