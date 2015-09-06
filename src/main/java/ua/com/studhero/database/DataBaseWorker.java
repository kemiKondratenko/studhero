package ua.com.studhero.database;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface DataBaseWorker {

    public <T> boolean save(T value);

    public <T> T get(long id, Class<T> objectClass);

    public <T> boolean update(T object);
}
