package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 27.09.2015.
 */
public class RemoveParamPreparedStatement extends MyPreparedStatement {
    private static final String removeListParamPreparedStatement =
            "DELETE FROM params WHERE object_id = ? AND attr_id = ? AND class_id = ? ";

    public RemoveParamPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(removeListParamPreparedStatement));
    }

    public void delete(long id, long attr_id, long classId) throws SQLException {
        execute(id, attr_id, classId);
    }

    private synchronized boolean execute(long id, long attr_id, long classId) throws SQLException {
        getPreparedStatement().setLong(1, id);
        getPreparedStatement().setLong(2, attr_id);
        getPreparedStatement().setLong(3, classId);
        return getPreparedStatement().execute();
    }
}

