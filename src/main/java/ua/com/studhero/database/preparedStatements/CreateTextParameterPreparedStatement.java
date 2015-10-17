package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eugene on 17.10.2015.
 */
public class CreateTextParameterPreparedStatement extends MyPreparedStatement {
    private static final String createTextParameterPreparedStatement =
            "INSERT INTO texts (text_id, text) VALUES (0, ?)";

    public CreateTextParameterPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(createTextParameterPreparedStatement, Statement.RETURN_GENERATED_KEYS));
    }

    public long create(String text) throws SQLException {
        ResultSet id = execute(text);
        long nId = 0;
        while (id.next()){
            nId = id.getLong(1);
        }
        return nId;
    }

    private synchronized ResultSet execute(String text) throws SQLException {
        getPreparedStatement().setString(1, text);
        getPreparedStatement().execute();
        return getPreparedStatement().getGeneratedKeys();
    }
}
