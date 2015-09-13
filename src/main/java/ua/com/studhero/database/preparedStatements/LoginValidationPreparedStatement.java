package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 13.09.2015.
 */
public class LoginValidationPreparedStatement extends MyPreparedStatement{
    private static final String loginValidationPreparedStatement =
                    "SELECT object_id" +
                    " FROM login_params" +
                    " WHERE login = ? ";

    public LoginValidationPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(loginValidationPreparedStatement));
    }

    public boolean isValid(String login) throws SQLException {
        ResultSet re = execute(login);
        while (re.next()){
            return false;
        }
        return true;
    }

    private synchronized ResultSet execute(String login) throws SQLException {
        getPreparedStatement().setString(1, login);
        return getPreparedStatement().executeQuery();
    }
}
