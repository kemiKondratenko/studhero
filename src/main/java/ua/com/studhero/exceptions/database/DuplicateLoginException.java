package ua.com.studhero.exceptions.database;

/**
 * Created by Eugene on 13.09.2015.
 */
public class DuplicateLoginException extends Exception{
    public DuplicateLoginException(String login) {
        super(String.format("Login {1} is not valid", login));
    }
}
