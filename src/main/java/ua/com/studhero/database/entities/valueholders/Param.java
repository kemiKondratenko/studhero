package ua.com.studhero.database.entities.valueholders;

/**
 * Created by Eugene on 06.09.2015.
 */
public class Param<T> {

    private T value;

    public Param(T value) {
        this.value = value;
    }

    public T get(){
        return value;
    }
}
