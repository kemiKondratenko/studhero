package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.entities.valueholders.Param;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class ObjectParamsPreparedStatement extends MyPreparedStatement {

    private static final String objectParamsQuery =  "SELECT param_id, param_value, param_type" +
                                                     " FROM params" +
                                                     " WHERE object_id = ?" +
                                                     " AND class_id = ?";

    public ObjectParamsPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(objectParamsQuery));
    }

    public Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException {
        Map<Long, Param> map = new HashMap<Long, Param>();
        ResultSet result = execute(objectId,classId);
        while (result.next()) {
            map.put(result.getLong(1), new Param(result.getObject(2), Class.forName(result.getString(3))));
        }
        return map;
    }

    private synchronized  ResultSet execute(long objectId, long classId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        return getPreparedStatement().executeQuery();
    }
}
