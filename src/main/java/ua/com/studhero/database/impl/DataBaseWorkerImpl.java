package ua.com.studhero.database.impl;

import ua.com.studhero.database.Connector;
import ua.com.studhero.database.DataBaseWorker;

/**
 * Created by Eugene on 06.09.2015.
 */
public class DataBaseWorkerImpl implements DataBaseWorker {

    private Connector connector;

    @Override
    public <T> boolean save(T value) {
        return false;
    }

    @Override
    public <T> T get(long id, Class<T> objectClass) {
        return null;
    }

    @Override
    public <T> boolean update(T object) {
        return false;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
