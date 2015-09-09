package ua.com.studhero.database.impl;

import ua.com.studhero.database.Connector;
import ua.com.studhero.database.QueryExecutor;
import ua.com.studhero.database.entities.valueholders.Param;
import ua.com.studhero.database.preparedStatements.AttrIdParamIdForObjectPreparedStatement;
import ua.com.studhero.database.preparedStatements.GetObjectParamsPreparedStatement;
import ua.com.studhero.database.preparedStatements.CreateObjectParamsPreparedStatement;
import ua.com.studhero.database.preparedStatements.SaveObjectParamsPreparedStatement;

import java.sql.SQLException;
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
    public boolean createParameter(long objectId, long attrId, Object value) throws SQLException {
        if(createObjectParamsPreparedStatement == null){
            createObjectParamsPreparedStatement = new CreateObjectParamsPreparedStatement(connector.getConnection());
        }
        return createObjectParamsPreparedStatement.save(objectId, attrId, value);
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
}
