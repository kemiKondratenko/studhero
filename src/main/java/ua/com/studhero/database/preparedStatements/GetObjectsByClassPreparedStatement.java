package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 10.09.2015.
 */
public class GetObjectsByClassPreparedStatement extends MyPreparedStatement{
    private static final String getObjectsByClassPreparedStatement =
            " SELECT object_id" +
            " FROM objects_class_relationship" +
            " WHERE class_id = ?" +
            " AND type_id = ?";

    public GetObjectsByClassPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(getObjectsByClassPreparedStatement));
    }

    public List<Long> get(long classId, long primary) throws SQLException {
        ResultSet resultSet = execute(classId, primary);
        List<Long> result = new ArrayList<Long>();
        while (resultSet.next()){
            result.add(resultSet.getLong(1));
        }
        return result;
    }

    private synchronized ResultSet execute(long classId, long primary) throws SQLException {
        getPreparedStatement().setLong(1, classId);
        getPreparedStatement().setLong(2, primary);
        return getPreparedStatement().executeQuery();
    }
}
