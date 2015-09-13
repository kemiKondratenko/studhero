package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 13.09.2015.
 */
public class UpdateParameterPreparedStatement extends MyPreparedStatement{
    private static final String updateParameterPreparedStatement =
                    " UPDATE params " +
                    " SET param_value = ?" +
                    " WHERE param_id = ?";

    public UpdateParameterPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(updateParameterPreparedStatement));
    }

    public boolean update(long param, Object fieldValue) throws SQLException {
        return execute(param, fieldValue);
    }

    private synchronized boolean execute(long param, Object fieldValue) throws SQLException {
        getPreparedStatement().setObject(1, fieldValue);
        getPreparedStatement().setLong(2, param);
        return getPreparedStatement().execute();
    }
}
