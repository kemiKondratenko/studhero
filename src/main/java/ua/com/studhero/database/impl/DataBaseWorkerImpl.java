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
    public <T extends BaseDBO> boolean save(T value) {
        return false;
    }

    @Override
    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException {

        log.info("START");
        T result = objectClass.newInstance();
        log.info(result.toString());
        ClassId classId = objectClass.getAnnotation(ClassId.class);
        log.info(classId.toString());
        Map<Long, Param> objectParams = queryExecutor.getObjectParams(id, classId.id());
        log.info("PAPAPAPA");
        for(Field field: objectClass.getDeclaredFields()){
            long paramId = field.getAnnotation(AttrId.class).id();
            log.info(" param " + paramId);
            field.set(result, objectParams.get(paramId).get());
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
