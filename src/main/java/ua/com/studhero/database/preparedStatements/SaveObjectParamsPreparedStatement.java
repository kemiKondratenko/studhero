package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 10.09.2015.
 */
public class SaveObjectParamsPreparedStatement extends MyPreparedStatement {

    private static final String saveObjectParamsPreparedStatement =
            "UPDATE params SET param_value = ? WHERE param_id = ?";


    public SaveObjectParamsPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(saveObjectParamsPreparedStatement));
    }

    public boolean save(long paramId, Object value) throws SQLException {
        return execute(paramId, value);
    }

    private synchronized boolean execute(long paramId, Object value) throws SQLException {
        getPreparedStatement().setLong(2, paramId);
        getPreparedStatement().setObject(1, value);
        return getPreparedStatement().execute();
    }
}
