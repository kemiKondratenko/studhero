package ua.com.studhero.database.queries.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 18.10.2015.
 */
public class BaseQueryExecutor {
    private Connection connection;

    public BaseQueryExecutor(Connection connection){
        this.connection = connection;
    }

    protected ResultSet execute(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public boolean closed() throws SQLException {
        return connection.isClosed();
    }
}
