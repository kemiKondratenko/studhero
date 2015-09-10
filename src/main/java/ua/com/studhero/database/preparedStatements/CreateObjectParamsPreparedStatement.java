package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 09.09.2015.
 */
public class CreateObjectParamsPreparedStatement extends MyPreparedStatement {

    private static final String createObjectParamPreparedStatement = " INSERT INTO params (param_id, param_value, object_id, attr_id, class_id) " +
                                                                    " VALUES (0, ?, ?,?, ?)";

    public CreateObjectParamsPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(createObjectParamPreparedStatement));
    }

    public boolean save(long objectId, long attrId, Object value, long classId) throws SQLException {
        return execute(objectId, attrId, value, classId);
    }

    private synchronized boolean execute(long objectId, long attrId, Object value, long classId) throws SQLException {
        getPreparedStatement().setObject(1, value);
        getPreparedStatement().setLong(2, objectId);
        getPreparedStatement().setLong(3, attrId);
        getPreparedStatement().setLong(4, classId);
        return getPreparedStatement().execute();
    }
}
