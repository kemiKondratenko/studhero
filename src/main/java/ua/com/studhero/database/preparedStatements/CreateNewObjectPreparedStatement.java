package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eugene on 10.09.2015.
 */
public class CreateNewObjectPreparedStatement extends MyPreparedStatement {
    private static final String createNewObjectPreparedStatement = "INSERT INTO objects (object_id, object_name) VALUES (0, ?)";

    public CreateNewObjectPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(createNewObjectPreparedStatement, Statement.RETURN_GENERATED_KEYS));
    }

    public long create(String name) throws SQLException {
        ResultSet id = execute(name);
        long nId = 0;
        while (id.next()){
            nId = id.getLong(1);
        }
        return nId;
    }

    private synchronized ResultSet execute(String name) throws SQLException {
        getPreparedStatement().setString(1, name);
        getPreparedStatement().execute();
        return getPreparedStatement().getGeneratedKeys();
    }
}
