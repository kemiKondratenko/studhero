package ua.com.studhero.database;

import ua.com.studhero.database.entities.BaseDBO;

import java.sql.SQLException;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface DataBaseWorker {

    public <T extends BaseDBO> boolean save(T value);

    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException;

    public <T extends BaseDBO> boolean update(T object);
}
