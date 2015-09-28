package ua.com.studhero.database.impl;

import ua.com.studhero.database.Connector;
import ua.com.studhero.database.QueryExecutor;
import ua.com.studhero.database.entities.valueholders.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.database.preparedStatements.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Eugene on 06.09.2015.
 */
public class QueryExecutorImpl implements QueryExecutor {

    Logger log = Logger.getLogger("Logger");

    private Connector connector;

    private GetObjectParamsPreparedStatement getobjectParamsPreparedStatement;
    private CreateObjectParamsPreparedStatement createObjectParamsPreparedStatement;
    private AttrIdParamIdForObjectPreparedStatement attrIdParamIdForObjectPreparedStatement;
    private SaveObjectParamsPreparedStatement saveObjectParamsPreparedStatement;
    private CreateNewObjectPreparedStatement createNewObjectPreparedStatement;
    private ObjectClassRelationshipPreparedStatement objectClassRelationshipPreparedStatement;
    private GetObjectsByClassPreparedStatement getObjectsByClassPreparedStatement;
    private GetObjectIdByUserPreparedStatement getObjectIdByUserPreparedStatement;
    private GetPrimaryClassIdPreparedStatement getPrimaryClassIdPreparedStatement;
    private CreateLoginablePreparedStatement createLoginablePreparedStatement;
    private LoginValidationPreparedStatement loginValidationPreparedStatement;
    private UpdateParameterPreparedStatement updateParameterPreparedStatement;
    private SimpleSearchPreparedStatement simpleSearchPreparedStatement;
    private RemoveListParamPreparedStatement removeListParamPreparedStatement;
    private RemoveParamPreparedStatement removeParamPreparedStatement;
    private GetobjectParamPreparedStatement getObjectParamPreparedStatement;

    @Override
    public Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException {
        if(getobjectParamsPreparedStatement == null || (getobjectParamsPreparedStatement != null && getobjectParamsPreparedStatement.closed())){
            getobjectParamsPreparedStatement = new GetObjectParamsPreparedStatement(connector.getConnection());
        }
        return getobjectParamsPreparedStatement.getObjectParams(objectId, classId);
    }


    @Override
    public Map<Long, Param> getObjectParam(long objectId, long attr_id, long classId) throws SQLException, ClassNotFoundException {
        if(getObjectParamPreparedStatement == null || (getObjectParamPreparedStatement != null && getObjectParamPreparedStatement.closed())){
            getObjectParamPreparedStatement = new GetobjectParamPreparedStatement(connector.getConnection());
        }
        return getObjectParamPreparedStatement.getObjectParams(objectId, attr_id, classId);
    }

    public boolean saveParameter(long paramId, Object value) throws SQLException {
        if(saveObjectParamsPreparedStatement == null || (saveObjectParamsPreparedStatement != null && saveObjectParamsPreparedStatement.closed())){
            saveObjectParamsPreparedStatement = new SaveObjectParamsPreparedStatement(connector.getConnection());
        }
        return saveObjectParamsPreparedStatement.save(paramId, value);
    }

    @Override
    public boolean createParameter(long objectId, long attrId, Object value, long classId) throws SQLException {
        if(value instanceof List){
            log.info("This is list, now");
            createParameter(objectId, attrId, (List<Long> ) value, classId);
        }else {
            log.info("save value "+ value);
            if(createObjectParamsPreparedStatement == null || (createObjectParamsPreparedStatement != null && createObjectParamsPreparedStatement.closed())){
                createObjectParamsPreparedStatement = new CreateObjectParamsPreparedStatement(connector.getConnection());
            }
            return createObjectParamsPreparedStatement.save(objectId, attrId, value, classId);
        }
        return false;
    }

    @Override
    public void createParameter(long objectId, long attrId, List<Long> listParam, long classId) throws SQLException {
        log.info("This is list, now");
        for(Long value: listParam){
            createParameter(objectId, attrId, value, classId);
        }
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Map<Long, Long> getAttrIdParamIdForObject(long objectId, long id) throws SQLException {
        if(attrIdParamIdForObjectPreparedStatement == null || (attrIdParamIdForObjectPreparedStatement != null && attrIdParamIdForObjectPreparedStatement.closed())){
            attrIdParamIdForObjectPreparedStatement = new AttrIdParamIdForObjectPreparedStatement(connector.getConnection());
        }
        return attrIdParamIdForObjectPreparedStatement.get(objectId, id);
    }

    @Override
    public long createNewObject(String name) throws SQLException {
        if(createNewObjectPreparedStatement == null || (createNewObjectPreparedStatement != null && createNewObjectPreparedStatement.closed())){
            createNewObjectPreparedStatement = new CreateNewObjectPreparedStatement(connector.getConnection());
        }
        return createNewObjectPreparedStatement.create(name);
    }

    @Override
    public void createObjectClassRelationship(long objectId, long classId, long typeId) throws SQLException {
        if(objectClassRelationshipPreparedStatement == null || (objectClassRelationshipPreparedStatement != null && objectClassRelationshipPreparedStatement.closed())){
            objectClassRelationshipPreparedStatement = new ObjectClassRelationshipPreparedStatement(connector.getConnection());
        }
        objectClassRelationshipPreparedStatement.create(objectId, classId, typeId);
    }

    @Override
    public List<Long> getObjectsByClass(long classId, long primary) throws SQLException {
        if(getObjectsByClassPreparedStatement == null || (getObjectsByClassPreparedStatement != null && getObjectsByClassPreparedStatement.closed())){
            getObjectsByClassPreparedStatement = new GetObjectsByClassPreparedStatement(connector.getConnection());
        }
        return getObjectsByClassPreparedStatement.get(classId, primary);
    }

    @Override
    public long getObjectIdByUser(String email, String password) throws SQLException {
        if(getObjectIdByUserPreparedStatement == null || (getObjectIdByUserPreparedStatement != null && getObjectIdByUserPreparedStatement.closed())){
            getObjectIdByUserPreparedStatement = new GetObjectIdByUserPreparedStatement(connector.getConnection());
        }
        return getObjectIdByUserPreparedStatement.get(email, password);
    }

    @Override
    public long getPrimaryClassId(long id) throws SQLException {
        if(getPrimaryClassIdPreparedStatement == null || (getPrimaryClassIdPreparedStatement != null && getPrimaryClassIdPreparedStatement.closed())){
            getPrimaryClassIdPreparedStatement = new GetPrimaryClassIdPreparedStatement(connector.getConnection());
        }
        return getPrimaryClassIdPreparedStatement.get(id);
    }

    @Override
    public boolean createLoginable(long object_id, String login, String password) throws SQLException {
        if(createLoginablePreparedStatement == null || (createLoginablePreparedStatement != null && createLoginablePreparedStatement.closed())){
            createLoginablePreparedStatement = new CreateLoginablePreparedStatement(connector.getConnection());
        }
        return createLoginablePreparedStatement.create(object_id, login, password);
    }

    @Override
    public boolean isLoginValid(String login) throws SQLException {
        if(loginValidationPreparedStatement == null || (loginValidationPreparedStatement != null && loginValidationPreparedStatement.closed())){
            loginValidationPreparedStatement = new LoginValidationPreparedStatement(connector.getConnection());
        }
        return loginValidationPreparedStatement.isValid(login);
    }

    @Override
    public boolean updateParameter(long param, Object fieldValue) throws SQLException {
        if(updateParameterPreparedStatement == null || (updateParameterPreparedStatement != null && updateParameterPreparedStatement.closed())){
            updateParameterPreparedStatement = new UpdateParameterPreparedStatement(connector.getConnection());
        }
        return updateParameterPreparedStatement.update(param, fieldValue);
    }

    @Override
    public boolean updateParameter(long object_id, long attr_id, Object value, long class_id) throws SQLException, ClassNotFoundException {
        if(value instanceof List){
            List<Long> valueList = (List<Long>) value;
            ListParam previousValues = (ListParam) getObjectParam(object_id, attr_id, class_id).get(attr_id);
            if(previousValues != null) {
                removeParams(object_id, attr_id, previousValues.difference(valueList), class_id);
                createParameter(object_id, attr_id, previousValues.newValues(valueList), class_id);
            }else
                createParameter(object_id, attr_id, value, class_id);
        }else {
            removeParam(object_id, attr_id, class_id);
            createParameter(object_id, attr_id, value, class_id);
        }
        return true;
    }

    @Override
    public List<Long> search(long paramAttrId, String paramValue) throws SQLException {
        if(simpleSearchPreparedStatement == null || (simpleSearchPreparedStatement != null && simpleSearchPreparedStatement.closed())){
            simpleSearchPreparedStatement = new SimpleSearchPreparedStatement(connector.getConnection());
        }
        return simpleSearchPreparedStatement.search(paramAttrId, paramValue);
    }

    public void removeParams(long id, long attr_id, List<Long> difference, long classId) throws SQLException {
        if(removeListParamPreparedStatement == null || (removeListParamPreparedStatement != null && removeListParamPreparedStatement.closed())){
            removeListParamPreparedStatement = new RemoveListParamPreparedStatement(connector.getConnection());
        }
        for(Long value: difference) {
            removeListParamPreparedStatement.delete(id, attr_id, value, classId);
        }
        return;
    }

    public void removeParam(long id, long attr_id, long classId) throws SQLException {
        if(removeParamPreparedStatement == null || (removeParamPreparedStatement != null && removeParamPreparedStatement.closed())){
            removeParamPreparedStatement = new RemoveParamPreparedStatement(connector.getConnection());
        }
        removeParamPreparedStatement.delete(id, attr_id, classId);
    }
}
