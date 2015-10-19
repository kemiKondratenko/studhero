package ua.com.studhero.database.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.studhero.database.Connector;
import ua.com.studhero.database.QueryExecutor;
import ua.com.studhero.database.entities.valueholders.TextParam;
import ua.com.studhero.database.entities.valueholders.TextValue;
import ua.com.studhero.database.entities.valueholders.base.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.database.preparedStatements.*;
import ua.com.studhero.database.queries.ListSearchQueryExecutor;
import ua.com.studhero.exceptions.database.DataBaseConsistensyError;

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
    private GetPrimaryObjectsByClassLimitedFromTo getPrimaryObjectsByClassLimitedFromTo;
    private GetTestValuePreparedStatement getTestValuePreparedStatement;
    private UpdateTextValuePreparedStatement updateTextValuePreparedStatement;
    private GetObjectParamByIdPreparedStatement getObjectParamByIdPreparedStatement;
    private CreateTextParameterPreparedStatement createTextParameterPreparedStatement;
    private ListSearchQueryExecutor listSearchQueryExecutor;

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
        return getObjectParamPreparedStatement.getObjectParam(objectId, attr_id, classId);
    }

    private Param getObjectParam(long param) throws SQLException {
        if(getObjectParamByIdPreparedStatement == null || (getObjectParamByIdPreparedStatement != null && getObjectParamByIdPreparedStatement.closed())){
            getObjectParamByIdPreparedStatement = new GetObjectParamByIdPreparedStatement(connector.getConnection());
        }
        return getObjectParamByIdPreparedStatement.get(param);
    }

    public boolean saveParameter(long paramId, Object value) throws SQLException {
        if(saveObjectParamsPreparedStatement == null || (saveObjectParamsPreparedStatement != null && saveObjectParamsPreparedStatement.closed())){
            saveObjectParamsPreparedStatement = new SaveObjectParamsPreparedStatement(connector.getConnection());
        }
        return saveObjectParamsPreparedStatement.save(paramId, value);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public boolean createParameter(long objectId, long attrId, Object value, long classId) throws SQLException {
        if(value instanceof List){
            log.info("This is list, now");
            createParameter(objectId, attrId, (List<Long> ) value, classId);
        }else {
            if(value instanceof TextValue){
                log.info("This is text, now");
                createParameter(objectId, attrId, (TextValue) value, classId);
            }else {
                log.info("save value " + value);
                if (createObjectParamsPreparedStatement == null || (createObjectParamsPreparedStatement != null && createObjectParamsPreparedStatement.closed())) {
                    createObjectParamsPreparedStatement = new CreateObjectParamsPreparedStatement(connector.getConnection());
                }
                return createObjectParamsPreparedStatement.save(objectId, attrId, value, classId);
            }
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void createParameter(long objectId, long attrId, TextValue textValue, long classId) throws SQLException {
        log.info("This is text, now");
        createParameter(objectId, attrId, createTextParameter(textValue.getValue()), classId);
    }

    private Long createTextParameter(String value) throws SQLException {
        log.info("This is text, now");
        if(createTextParameterPreparedStatement == null || (createTextParameterPreparedStatement != null && createTextParameterPreparedStatement.closed())){
            createTextParameterPreparedStatement = new CreateTextParameterPreparedStatement(connector.getConnection());
        }
        return createTextParameterPreparedStatement.create(value);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void createParameter(long objectId, long attrId, List<Long> listParam, long classId) throws SQLException {
        log.info("This is list, now");
        for(Long value: listParam){
            createParameter(objectId, attrId, value, classId);
        }
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
    @Transactional(propagation = Propagation.NESTED)
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
    @Transactional(propagation = Propagation.NESTED)
    public boolean updateParameter(long param, Object fieldValue) throws SQLException, DataBaseConsistensyError {
        if (fieldValue instanceof TextValue){
            return updateParameter(param, (TextValue) fieldValue);
        }else {
            if (updateParameterPreparedStatement == null || (updateParameterPreparedStatement != null && updateParameterPreparedStatement.closed())) {
                updateParameterPreparedStatement = new UpdateParameterPreparedStatement(connector.getConnection());
            }
            return updateParameterPreparedStatement.update(param, fieldValue);
        }
    }
    public boolean updateParameter(long param, TextValue fieldValue) throws SQLException, DataBaseConsistensyError {
        log.info("updateParameter TextValue with param value "+param+ " and value "+fieldValue);

        if (updateTextValuePreparedStatement == null || (updateTextValuePreparedStatement != null && updateTextValuePreparedStatement.closed())) {
            updateTextValuePreparedStatement = new UpdateTextValuePreparedStatement(connector.getConnection());
        }

        if(fieldValue.getId() == null){
            log.info("updateParameter TextValue " +fieldValue.getId());
            Param textParam = getObjectParam(param);
            if( textParam != null) {
                log.info("updateParameter TextValue " +textParam);

                fieldValue.setId(((TextParam) textParam).get().getId());
                return updateTextValuePreparedStatement.update(fieldValue.getId(), fieldValue.getValue());
            } else {
                throw new DataBaseConsistensyError("Cant find text value for param with "+ param);
            }
        }else {
            log.info("updateParameter TextValue " +fieldValue.getId());

            return updateTextValuePreparedStatement.update(fieldValue.getId(), fieldValue.getValue());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public boolean updateParameter(long object_id, long attr_id, Object value, long class_id) throws SQLException, ClassNotFoundException, DataBaseConsistensyError {
        if(value instanceof List){
            log.info("List");
            updateParameter(object_id, attr_id, (List<Long>) value, class_id);
        }else {
            if (value instanceof TextValue){
                log.info("TextValue");
                updateParameter(object_id, attr_id, (TextValue) value, class_id);
            }else {
                log.info("Not a List");
                removeParam(object_id, attr_id, class_id);
                createParameter(object_id, attr_id, value, class_id);
            }
        }
        return true;
    }

    public boolean updateParameter(long object_id, long attr_id, List<Long> value, long class_id) throws SQLException, ClassNotFoundException {
        log.info("List"+value);
        Param e = getObjectParam(object_id, attr_id, class_id).get(attr_id);
        log.info("List"+e);
        ListParam previousValues = (ListParam) e;
        log.info(""+previousValues);
        if(previousValues != null) {
            removeParams(object_id, attr_id, previousValues.difference(value), class_id);
            createParameter(object_id, attr_id, previousValues.newValues(value), class_id);
        }else
            createParameter(object_id, attr_id, value, class_id);
        return true;
    }

    public boolean updateParameter(long object_id, long attr_id, TextValue value, long class_id) throws SQLException, ClassNotFoundException, DataBaseConsistensyError {
        log.info("updateParameter TextValue with find param");
        Param param = getObjectParam(object_id, attr_id, class_id).get(attr_id);
        log.info("TextValue "+ param);
        if(param == null){
            createParameter(object_id, attr_id, createTextParameter(value.getValue()), class_id);
        }else {
            value.setId(((TextValue)param.get()).getId());
            updateParameter(param.getId(), value);
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

    @Override
    public List<Long> search(List<Long> paramAttrIds, List<Long> textAttrIds, String paramValue, long class_id) throws SQLException {
        if(listSearchQueryExecutor == null || (listSearchQueryExecutor != null && listSearchQueryExecutor.closed())){
            listSearchQueryExecutor = new ListSearchQueryExecutor(connector.getConnection());
        }
        return listSearchQueryExecutor.search(paramAttrIds, textAttrIds, paramValue, class_id);
    }

    @Override
    public List<Long> getPrimaryObjectsByClassLimitedFromTo(long class_id, long from, long to) throws SQLException {
        if(getPrimaryObjectsByClassLimitedFromTo == null || (getPrimaryObjectsByClassLimitedFromTo != null && getPrimaryObjectsByClassLimitedFromTo.closed())){
            getPrimaryObjectsByClassLimitedFromTo = new GetPrimaryObjectsByClassLimitedFromTo(connector.getConnection());
        }
        return getPrimaryObjectsByClassLimitedFromTo.get(class_id, from, to);
    }

    @Override
    public String getTestValue(Long id) throws SQLException {
        if(getTestValuePreparedStatement == null || (getTestValuePreparedStatement != null && getTestValuePreparedStatement.closed())){
            getTestValuePreparedStatement = new GetTestValuePreparedStatement(connector.getConnection());
        }
        return getTestValuePreparedStatement.get(id);
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

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
