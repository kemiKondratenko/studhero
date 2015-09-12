package ua.com.studhero.database.impl;

import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.constants.RelationshipTypes;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.Param;
import ua.com.studhero.model.entity.User;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Eugene on 06.09.2015.
 */
public class DataBaseWorkerImpl implements DataBaseWorker {

    Logger log = Logger.getLogger("Logger");

    private QueryExecutorImpl queryExecutor;

    @Override
    public <T extends BaseDBO> long save(T value) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        long objectId =  queryExecutor.createNewObject("pipipipi");
        long classId = value.getClass().getAnnotation(ClassId.class).id();
        queryExecutor.objectClassRelationship(objectId, classId, RelationshipTypes.primary);
        for(Field field: value.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            long attr_id;
            if(field.getAnnotation(AttrId.class) != null)
                attr_id = field.getAnnotation(AttrId.class).id();
            else continue;
            Object fieldValue = field.get(value);
            queryExecutor.createParameter(objectId, attr_id, fieldValue, classId);
        }
        setId(value, objectId);
        return objectId;
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
            long attrId;
            if(field.getAnnotation(AttrId.class) != null)
                attrId = field.getAnnotation(AttrId.class).id();
            else continue;
            Param<?> param = objectParams.get(attrId);
            if(param != null)
                field.set(result, param.get());
        }
        setId(result, id);
        return result;
    }

    @Override
    public <T extends BaseDBO> List<T> get(Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        List<Long> objects = queryExecutor.getObjectsByClass(objectClass.getAnnotation(ClassId.class).id(), RelationshipTypes.primary);
        List<T> result = new ArrayList<T>();
        for (Long id: objects){
            result.add(get(id, objectClass));
        }
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

    @Override
    public long getIdIfExists(User object) throws SQLException {
        return queryExecutor.getObjectIdByUser(object.getEmail(), object.getPassword());
    }

    @Override
    public long getPrimaryClassId(long id) throws SQLException {
        return queryExecutor.getPrimaryClassId(id);
    }

    public void setQueryExecutor(QueryExecutorImpl queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

}
