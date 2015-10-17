package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 17.10.2015.
 */
public class GetTestValuePreparedStatement extends MyPreparedStatement{
    private static final String getTestValuePreparedStatement =
            " SELECT text " +
            " FROM texts" +
            " WHERE text_id = ?";

    public GetTestValuePreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(getTestValuePreparedStatement));
    }

    public String get(Long id) throws SQLException {
        ResultSet resultSet = execute(id);
        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    private ResultSet execute(Long id) throws SQLException {
        getPreparedStatement().setLong(1, id);
        return getPreparedStatement().executeQuery();
    }
}
