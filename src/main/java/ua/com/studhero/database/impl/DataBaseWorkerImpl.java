package ua.com.studhero.database.impl;

import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.Param;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Map;

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
    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException {
        T result = objectClass.newInstance();
        ClassId classId = objectClass.getAnnotation(ClassId.class);
        Map<Long, Param> objectParams = queryExecutor.getObjectParams(id, classId.id());
        for(Field field: objectClass.getDeclaredFields()){
            Class fieldClass = field.getType();
            long paramId = field.getAnnotation(AttrId.class).id();
            field.set(result, objectParams.get(paramId).get(fieldClass));
        }
        return result;
    }

    @Override
    public <T extends BaseDBO> boolean update(T object) {
        return false;
    }

    public void setQueryExecutor(QueryExecutorImpl queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

}
