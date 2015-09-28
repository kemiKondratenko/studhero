package ua.com.studhero.database.preparedStatements.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Eugene on 06.09.2015.
 */
public class MyPreparedStatement {
    private PreparedStatement preparedStatement;

    public MyPreparedStatement(PreparedStatement preparedStatement){
        this.preparedStatement = preparedStatement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public boolean closed() throws SQLException {
        return preparedStatement.isClosed() || preparedStatement.getConnection().isClosed();
    }

}
