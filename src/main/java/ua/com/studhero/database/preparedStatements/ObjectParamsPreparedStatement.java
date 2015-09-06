package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.entities.valueholders.Param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 06.09.2015.
 */
public class ObjectParamsPreparedStatement extends MyPreparedStatement {

    private static final String objectParamsQuery = "";

    public ObjectParamsPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(objectParamsQuery));
    }

    public Map<Long, Param> getObjectParams(long objectId, long classId) {
        Map<Long, Param> map = new HashMap<Long, Param>();
        map.put(1l, new Param("", String.class));
        return map;
    }
}
