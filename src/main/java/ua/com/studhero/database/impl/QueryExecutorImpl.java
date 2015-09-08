package ua.com.studhero.database.impl;

import ua.com.studhero.database.Connector;
import ua.com.studhero.database.QueryExecutor;
import ua.com.studhero.database.entities.valueholders.Param;
import ua.com.studhero.database.preparedStatements.ObjectParamsPreparedStatement;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Eugene on 06.09.2015.
 */
public class QueryExecutorImpl implements QueryExecutor {

    Logger log = Logger.getLogger("Logger");

    private Connector connector;

    ObjectParamsPreparedStatement objectParamsPreparedStatement;

    @Override
    public Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException {
        if(objectParamsPreparedStatement == null){
            log.info("START");
            objectParamsPreparedStatement = new ObjectParamsPreparedStatement(connector.getConnection());
            log.info("FIN");
        }
        return objectParamsPreparedStatement.getObjectParams(objectId, classId);
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
