package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Eugene on 27.09.2015.
 */
public class RemoveListParamPreparedStatement extends MyPreparedStatement{
    private static final String removeListParamPreparedStatement =
            "DELETE FROM params WHERE object_id = ? AND attr_id = ? AND param_value = ? AND class_id = ? ";

    public RemoveListParamPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(removeListParamPreparedStatement));
    }

    public void delete(long id, long attr_id, Object objects, long classId) throws SQLException {
        execute(id, attr_id, objects, classId);
    }

    private synchronized boolean execute(long id, long attr_id, Object objects, long classId) throws SQLException {
        getPreparedStatement().setLong(1, id);
        getPreparedStatement().setLong(2, attr_id);
        getPreparedStatement().setObject(3, objects);
        getPreparedStatement().setLong(4, classId);
        return getPreparedStatement().execute();
    }
}
