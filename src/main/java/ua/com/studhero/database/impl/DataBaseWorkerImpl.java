package ua.com.studhero.database.impl;

import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.FullLoad;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.QueryExecutor;
import ua.com.studhero.database.constants.ClassFactory;
import ua.com.studhero.database.constants.RelationshipTypes;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;
import ua.com.studhero.database.entities.valueholders.BaseDBOListParam;
import ua.com.studhero.database.entities.valueholders.BaseDBOParam;
import ua.com.studhero.database.entities.valueholders.IdListParam;
import ua.com.studhero.database.entities.valueholders.TextParam;
import ua.com.studhero.database.entities.valueholders.base.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.exceptions.database.DataBaseConsistensyError;
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
    @Transactional
    public <T extends BaseDBO> long save(T value) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, DataBaseConsistensyError {
        long objectId =  queryExecutor.createNewObject(value.getClass().getName());
        return save(objectId, value);
    }

    @Override
    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        return get(id, objectClass, false);
    }

    @Override
    public <T extends BaseDBO> T getFull(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        return get(id, objectClass, true);
    }

    @Override
    public BaseDBO get(Long id) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        log.info("class" + ClassFactory.getClassById(getPrimaryClassId(id)));
        return get(id, ClassFactory.getClassById(getPrimaryClassId(id)), false);
    }

    @Override
    public BaseDBO getFull(Long id) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        log.info("class" + ClassFactory.getClassById(getPrimaryClassId(id)));
        return get(id, ClassFactory.getClassById(getPrimaryClassId(id)), true);
    }

    @Override
    public <T extends BaseDBO> List<T> get(Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        List<Long> objects = queryExecutor.getObjectsByClass(objectClass.getAnnotation(ClassId.class).id(), RelationshipTypes.primary);
        List<T> result = new ArrayList<T>();
        for (Long id: objects){
            result.add(get(id, objectClass, false));
        }
        return result;
    }

    @Override
    @Transactional
    public long save(long id, BaseDBO entity) throws SQLException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, DataBaseConsistensyError {
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
                    log.info("start value without id ");
                    queryExecutor.updateParameter(id, attr_id, fieldValue.getBaseForm(), classId);
                    log.info("saved");
                }else {
                    log.info("start value with id "+fieldValue.getId());
                    queryExecutor.updateParameter(fieldValue.getId(), fieldValue.getBaseForm());
                    log.info("saved");
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
    public <T extends BaseDBO> boolean update(T object) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException, DataBaseConsistensyError {
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
    public boolean isLoginValid(String login) throws SQLException {
        return queryExecutor.isLoginValid(login);
    }

    @Override
    public List<Long> search(SearchScope searchScope) throws SQLException {
        return queryExecutor.search(searchScope.getParamAttrIds().get(0), searchScope.getParamValue());
    }

    @Override
    public List<Long> search(SearchScope searchScope, long class_id) throws SQLException {
        return queryExecutor.search(searchScope.getParamAttrIds(), searchScope.getTextAttrIds(),searchScope.getParamValue(), class_id);
    }

    @Override
    public <T extends BaseDBO>  List<T> get(Class<T> classValue, Long from, Long to) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        long class_id = classValue.getAnnotation(ClassId.class).id();
        return get(queryExecutor.getPrimaryObjectsByClassLimitedFromTo(class_id, from.longValue(), to.longValue()), classValue);
    }

    private  <T extends BaseDBO> T get(long id, Class<T> objectClass, boolean full) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
        List<Long> noNeedToLoad = Lists.newArrayList();
        return get(id, objectClass, noNeedToLoad, full);
    }

    private  <T extends BaseDBO> T get(long id, Class<T> objectClass, List<Long> noNeedToLoad, boolean full) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException {
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
            if(param != null) {
                if(field.getAnnotation(FullLoad.class) == null) {
                    field.set(result, get(param));
                }else {
                    if(full) {
                        noNeedToLoad.add(id);
                        field.set(result, get(param, noNeedToLoad));
                    }
                }
            }
        }
        log.info(result.toString());
        setId(result, id);
        return result;
    }

    private Param<?> get(Param<?> param) throws SQLException {
        if (param instanceof TextParam){
            ((TextParam) param).get().setValue(
                    queryExecutor.getTestValue(((TextParam) param).get().getId()));
        }
        return param;
    }

    private <T> Param<T> get(Param<T> param, List<Long> noNeedToLoad) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        if (param instanceof BaseDBOParam){
            Long id = ((BaseDBOParam) param).get().getObjectId();
            if(!noNeedToLoad.contains(id)) {
                param.set((T) getFull(id, noNeedToLoad));
            }
        }else {
            if (param instanceof BaseDBOListParam){
                List<BaseDBO> res = Lists.newArrayList();
                for (BaseDBO object: ((BaseDBOListParam) param).get()){
                    res.add(get(object.getObjectId()));
                }
                param.set((T) res);
            }
        }
        return param;
    }

    private BaseDBO getFull(Long id, List<Long> noNeedToLoad) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        log.info("class" + ClassFactory.getClassById(getPrimaryClassId(id)));
        return get(id, ClassFactory.getClassById(getPrimaryClassId(id)), noNeedToLoad, true);
    }

    private <T extends BaseDBO> void setId(T result, long id) throws NoSuchFieldException, IllegalAccessException {
        Field f = BaseDBO.class.getDeclaredField("objectId");
        f.setAccessible(true);
        f.set(result, id);
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
