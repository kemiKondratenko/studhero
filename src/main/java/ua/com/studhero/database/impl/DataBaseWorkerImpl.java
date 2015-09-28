package ua.com.studhero.database.impl;

import com.google.common.collect.Lists;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.QueryExecutor;
import ua.com.studhero.database.constants.ClassFactory;
import ua.com.studhero.database.constants.RelationshipTypes;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;
import ua.com.studhero.database.entities.valueholders.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.exceptions.database.DuplicateLoginException;
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

    private QueryExecutor queryExecutor;

    @Override
    public <T extends BaseDBO> long save(T value) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        long objectId =  queryExecutor.createNewObject(value.getClass().getName());
        return save(objectId, value);
    }

    @Override
    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        T result = objectClass.newInstance();
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
                field.set(result, param);
        }
        log.info(result.toString());
        setId(result, id);
        return result;
    }

    @Override
    public BaseDBO get(Long id) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        log.info("class" + ClassFactory.getClassById(getPrimaryClassId(id)));
        return get(id, ClassFactory.getClassById(getPrimaryClassId(id)));
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
    public <T extends BaseDBO> boolean update(T object) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException {
        save(object.getObjectId(), object);
        return true;
    }

    @Override
    public long getIdIfExists(User object) throws SQLException {
        return queryExecutor.getObjectIdByUser(object.getEmail(), object.getPassword());
    }

    @Override
    public long getPrimaryClassId(long id) throws SQLException {
        return queryExecutor.getPrimaryClassId(id);
    }

    @Override
    public long createLoginable(String login, String password) throws SQLException, DuplicateLoginException {
        if(!isLoginValid(login)) throw new DuplicateLoginException(login);
        long object_id =  queryExecutor.createNewObject(login);
        queryExecutor.createLoginable(object_id, login, password);
        return object_id;
    }

    @Override
    public long save(long id, BaseDBO entity) throws SQLException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        long classId = entity.getClass().getAnnotation(ClassId.class).id();
        long primaryClassId = queryExecutor.getPrimaryClassId(id);
        if(primaryClassId == 0)
            queryExecutor.createObjectClassRelationship(id, classId, RelationshipTypes.primary);
        log.info("C"+classId+" "+primaryClassId);
        for(Field field: entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Param<?> fieldValue = (Param<?>) field.get(entity);
            if(fieldValue == null) continue;
            long attr_id;
            AttrId attr_id_annotation = field.getAnnotation(AttrId.class);
            if(attr_id_annotation != null)
                attr_id = attr_id_annotation.id();
            else continue;
            log.info("F"+fieldValue+" "+primaryClassId);
            if(primaryClassId != 0) {
                if(fieldValue.getId() == 0 || fieldValue instanceof ListParam){
                    log.info("start savedOther 1 ");
                    queryExecutor.updateParameter(id, attr_id, fieldValue.getBaseForm(), classId);
                    log.info("savedOther");
                }else {
                    log.info("start savedOther 2");
                    queryExecutor.updateParameter(fieldValue.getId(), fieldValue.getBaseForm());
                    log.info("savedOther");
                }
            }else{
                log.info("start create");
                queryExecutor.createParameter(id, attr_id, fieldValue.getBaseForm(), classId);
                log.info("created" + fieldValue.getBaseForm());
            }
        }
        setId(entity, id);
        return id;
    }

    @Override
    public boolean isLoginValid(String login) throws SQLException {
        return queryExecutor.isLoginValid(login);
    }

    @Override
    public List<Long> search(SearchScope searchScope) throws SQLException {
        return queryExecutor.search(searchScope.getParamAttrId(), searchScope.getParamValue());
    }

    @Override
    public <T extends BaseDBO>  List<T> get(Class<T> classValue, Long from, Long to) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        long class_id = classValue.getAnnotation(ClassId.class).id();
        return get(queryExecutor.getPrimaryObjectsByClassLimitedFromTo(class_id, from.longValue(), to.longValue()), classValue);
    }

    private <T extends BaseDBO> List<T> get(List<Long> objects, Class<T> classValue) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<T> res = Lists.newArrayList();
        for (Long id: objects){
            res.add(get(id, classValue));
        }
        return res;
    }

    public void setQueryExecutor(QueryExecutorImpl queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

}
