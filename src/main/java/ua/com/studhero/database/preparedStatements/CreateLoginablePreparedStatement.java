package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 13.09.2015.
 */
public class CreateLoginablePreparedStatement  extends MyPreparedStatement{
    private static final String createLoginablePreparedStatement =
                    "INSERT INTO login_params" +
                    " (object_id, login, password, login_id) " +
                    " VALUES (?, ?, ?, 0)";

    public CreateLoginablePreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(createLoginablePreparedStatement, java.sql.Statement.RETURN_GENERATED_KEYS));
    }

    public boolean create(long object_id, String login, String password) throws SQLException {
        return execute(object_id, login, password);
    }

    private synchronized boolean execute(long object_id, String login, String password) throws SQLException {
        getPreparedStatement().setLong(1, object_id);
        getPreparedStatement().setString(2, login);
        getPreparedStatement().setString(3, password);
        return getPreparedStatement().execute();
    }
}
