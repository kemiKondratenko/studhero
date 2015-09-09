package ua.com.studhero.database.impl;

import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.database.Connector;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.Param;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Eugene on 06.09.2015.
 */
public class DataBaseWorkerImpl implements DataBaseWorker {

    Logger log = Logger.getLogger("Logger");

    private QueryExecutorImpl queryExecutor;

    @Override
    public <T extends BaseDBO> boolean save(T value) throws SQLException, ClassNotFoundException, IllegalAccessException {
        long objectId = value.getObjectId();
        for(Field field: value.getClass().getDeclaredFields()) {
            long attr_id = field.getAnnotation(AttrId.class).id();
            Object fieldValue = field.get(value);
            queryExecutor.createParameter(objectId, attr_id, fieldValue);
        }
        return false;
    }

    @Override
    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        T result = objectClass.newInstance();
        log.info(result.toString());
        ClassId classId = objectClass.getAnnotation(ClassId.class);
        log.info(classId.toString());
        Map<Long, Param> objectParams = queryExecutor.getObjectParams(id, classId.id());
        for(Field field: objectClass.getDeclaredFields()){
            field.setAccessible(true);
            long attrId = field.getAnnotation(AttrId.class).id();
            field.set(result, objectParams.get(attrId).get());
        }
        setId(result, id);
        return result;
    }

    private <T extends BaseDBO> void setId(T result, long id) throws NoSuchFieldException, IllegalAccessException {
        Field f = BaseDBO.class.getDeclaredField("objectId");
        f.setAccessible(true);
        f.set(result, id);
    }

    @Override
    public <T extends BaseDBO> boolean update(T object) {
        return false;
    }

    public void setQueryExecutor(QueryExecutorImpl queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

}
