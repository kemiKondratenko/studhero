package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 11.09.2015.
 */
public class GetObjectIdByUserPreparedStatement extends MyPreparedStatement{
    private static final String getObjectIdByUserPreparedStatement =
                    "SELECT object_id" +
                    " FROM login_params" +
                    " WHERE login = ? " +
                    " AND password = ?";

    public GetObjectIdByUserPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(getObjectIdByUserPreparedStatement));
    }

    public long get(String email, String password) throws SQLException {
        long id = 0;
        ResultSet re = execute(email, password);
        while (re.next()){
            id = re.getLong(1);
        }
        return id;
    }

    private synchronized ResultSet execute(String email, String password) throws SQLException {
        getPreparedStatement().setString(1, email);
        getPreparedStatement().setString(2, password);
        return getPreparedStatement().executeQuery();
    }
}
