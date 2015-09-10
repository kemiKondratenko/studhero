package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 09.09.2015.
 */
public class CreateObjectParamsPreparedStatement extends MyPreparedStatement {

    private static final String createObjectParamPreparedStatement = " INSERT INTO params (param_id, param_value, object_id, attr_id) " +
                                                                    " VALUES (?, ?, ?,?)";

    public CreateObjectParamsPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(createObjectParamPreparedStatement));
    }

    public boolean save(long objectId, long attrId, Object value) throws SQLException {
        return execute(objectId, attrId, value);
    }

    private synchronized boolean execute(long objectId, long attrId, Object value) throws SQLException {
        getPreparedStatement().setLong(1, 0);
        getPreparedStatement().setObject(2, value);
        getPreparedStatement().setLong(3, objectId);
        getPreparedStatement().setLong(4, attrId);
        return getPreparedStatement().execute();
    }
}
