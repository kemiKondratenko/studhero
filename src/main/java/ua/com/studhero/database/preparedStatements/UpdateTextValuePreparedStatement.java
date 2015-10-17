package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 17.10.2015.
 */
public class UpdateTextValuePreparedStatement extends MyPreparedStatement{
    private static final String updateTextValuePreparedStatement =
                    " UPDATE texts " +
                    " SET text = ? " +
                    " WHERE text_id = ?";

    public UpdateTextValuePreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(updateTextValuePreparedStatement));
    }

    public boolean update(long id, String value) throws SQLException {
        return execute(id, value);
    }

    private boolean execute(long id, String value) throws SQLException {
        getPreparedStatement().setString(1, value);
        getPreparedStatement().setLong(2, id);
        return getPreparedStatement().execute();
    }
}
