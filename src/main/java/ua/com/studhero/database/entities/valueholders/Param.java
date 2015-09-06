package ua.com.studhero.database.entities.valueholders;


/**
 * Created by Eugene on 06.09.2015.
 */
public class Param {
    Object value;
    Class type;

    public Param(Object value, Class type) {
        this.value = value;
        this.type = type;
    }

    public <T> T get(Class<T> fieldClass) {
        if(fieldClass.equals(type))
            return (T) value;
        return null;
    }
}
