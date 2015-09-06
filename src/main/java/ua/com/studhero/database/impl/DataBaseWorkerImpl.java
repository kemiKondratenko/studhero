package ua.com.studhero.database.impl;

import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.BaseDBO;

import java.lang.reflect.Field;

/**
 * Created by Eugene on 06.09.2015.
 */
public class DataBaseWorkerImpl implements DataBaseWorker {

    private QueryExecutorImpl queryExecutor;

    @Override
    public <T extends BaseDBO> boolean save(T value) {
        return false;
    }

    @Override
    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException {
        T result = objectClass.newInstance();
        for(Field field: objectClass.getDeclaredFields()){

        }
        return null;
    }

    @Override
    public <T extends BaseDBO> boolean update(T object) {
        return false;
    }

    public void setQueryExecutor(QueryExecutorImpl queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

}
