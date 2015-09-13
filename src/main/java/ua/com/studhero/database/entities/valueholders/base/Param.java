package ua.com.studhero.database.entities.valueholders.base;

/**
 * Created by Eugene on 06.09.2015.
 */
public class Param<T> {

    private final long param_id;
    private T value;

    public Param(long param_id, T value) {
        this.param_id = param_id;
        this.value = value;
    }

    public T get(){
        return value;
    }

    public long getId() {
        return param_id;
    }
}
