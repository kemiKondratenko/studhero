package ua.com.studhero.exceptions.database;

/**
 * Created by Eugene on 17.10.2015.
 */
public class DataBaseConsistensyError extends Exception {
    public DataBaseConsistensyError(String error) {
        super(error);
    }
}
