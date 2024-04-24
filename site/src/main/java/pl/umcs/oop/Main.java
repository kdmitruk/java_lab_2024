package pl.umcs.oop;

import pl.umcs.oop.auth.Account;
import pl.umcs.oop.auth.AccountManager;
import pl.umcs.oop.database.DatabaseConnection;

import javax.naming.AuthenticationException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect("my.db");

        AccountManager accountManager = new AccountManager(dbConnection);
        accountManager.init();

   //     accountManager.register("notch", "verysecurepassword");

        try {
            Account notch = accountManager.authenticate("notch", "verysecurepassword");
            System.out.println(notch);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        dbConnection.disconnect();
    }
}