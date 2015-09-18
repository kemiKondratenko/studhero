package ua.com.studhero.database.impl;

import ua.com.studhero.database.Connector;
import ua.com.studhero.database.QueryExecutor;
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

    @Override
    public Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException {
        if(getobjectParamsPreparedStatement == null){
            getobjectParamsPreparedStatement = new GetObjectParamsPreparedStatement(connector.getConnection());
        }
        return getobjectParamsPreparedStatement.getObjectParams(objectId, classId);
    }

    @Override
    public boolean saveParameter(long paramId, Object value) throws SQLException {
        if(saveObjectParamsPreparedStatement == null){
            saveObjectParamsPreparedStatement = new SaveObjectParamsPreparedStatement(connector.getConnection());
        }
        return saveObjectParamsPreparedStatement.save(paramId, value);
    }

    @Override
    public boolean createParameter(long objectId, long attrId, Object value, long classId) throws SQLException {
        if(createObjectParamsPreparedStatement == null){
            createObjectParamsPreparedStatement = new CreateObjectParamsPreparedStatement(connector.getConnection());
        }
        return createObjectParamsPreparedStatement.save(objectId, attrId, value, classId);
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Map<Long, Long> getAttrIdParamIdForObject(long objectId, long id) throws SQLException {
        if(attrIdParamIdForObjectPreparedStatement == null){
            attrIdParamIdForObjectPreparedStatement = new AttrIdParamIdForObjectPreparedStatement(connector.getConnection());
        }
        return attrIdParamIdForObjectPreparedStatement.get(objectId, id);
    }

    public long createNewObject(String name) throws SQLException {
        if(createNewObjectPreparedStatement == null){
            createNewObjectPreparedStatement = new CreateNewObjectPreparedStatement(connector.getConnection());
        }
        return createNewObjectPreparedStatement.create(name);
    }

    public void objectClassRelationship(long objectId, long classId, long typeId) throws SQLException {
        if(objectClassRelationshipPreparedStatement == null){
            objectClassRelationshipPreparedStatement = new ObjectClassRelationshipPreparedStatement(connector.getConnection());
        }
        objectClassRelationshipPreparedStatement.create(objectId, classId, typeId);
    }

    public List<Long> getObjectsByClass(long classId, long primary) throws SQLException {
        if(getObjectsByClassPreparedStatement == null){
            getObjectsByClassPreparedStatement = new GetObjectsByClassPreparedStatement(connector.getConnection());
        }
        return getObjectsByClassPreparedStatement.get(classId, primary);
    }

    public long getObjectIdByUser(String email, String password) throws SQLException {
        if(getObjectIdByUserPreparedStatement == null){
            getObjectIdByUserPreparedStatement = new GetObjectIdByUserPreparedStatement(connector.getConnection());
        }
        return getObjectIdByUserPreparedStatement.get(email, password);
    }

    public long getPrimaryClassId(long id) throws SQLException {
        if(getPrimaryClassIdPreparedStatement == null){
            getPrimaryClassIdPreparedStatement = new GetPrimaryClassIdPreparedStatement(connector.getConnection());
        }
        return getPrimaryClassIdPreparedStatement.get(id);
    }

    public boolean createLoginable(long object_id, String login, String password) throws SQLException {
        if(createLoginablePreparedStatement == null){
            createLoginablePreparedStatement = new CreateLoginablePreparedStatement(connector.getConnection());
        }
        return createLoginablePreparedStatement.create(object_id, login, password);
    }

    public boolean isLoginValid(String login) throws SQLException {
        if(loginValidationPreparedStatement == null){
            loginValidationPreparedStatement = new LoginValidationPreparedStatement(connector.getConnection());
        }
        return loginValidationPreparedStatement.isValid(login);
    }

    public boolean updateParameter(long param, Object fieldValue) throws SQLException {
        if(updateParameterPreparedStatement == null){
            updateParameterPreparedStatement = new UpdateParameterPreparedStatement(connector.getConnection());
        }
        return updateParameterPreparedStatement.update(param, fieldValue);
    }

    public List<Long> search(long paramAttrId, String paramValue) throws SQLException {
        if(simpleSearchPreparedStatement == null){
            simpleSearchPreparedStatement = new SimpleSearchPreparedStatement(connector.getConnection());
        }
        return simpleSearchPreparedStatement.search(paramAttrId, paramValue);
    }
}
