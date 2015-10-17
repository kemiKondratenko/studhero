package ua.com.studhero.database.preparedStatements;

import com.google.common.collect.Lists;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.*;
import ua.com.studhero.database.entities.valueholders.base.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;
import ua.com.studhero.database.preparedStatements.base.ParamCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Eugene on 27.09.2015.
 */
public class GetobjectParamPreparedStatement extends ParamCreator {

    Logger log = Logger.getLogger("Logger");

    private static final String objectParamQuery = " SELECT attrs_id, param_value, param_id, attrs_type_id " +
            " FROM attrs INNER JOIN params on attrs.attrs_id = params.attr_id " +
            " WHERE object_id = ? " +
            " AND class_id = ? " +
            " AND attr_id = ? ";

    public GetobjectParamPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(objectParamQuery));
    }

    public Map<Long, Param> getObjectParam(long objectId, long attr_id, long classId) throws SQLException, ClassNotFoundException {
        Map<Long, Param> map = new HashMap<Long, Param>();
        ResultSet result = execute(objectId, attr_id, classId);
        while (result.next()) {
            if (map.containsKey(result.getLong(1))) {
                ((ListParam) map.get(result.getLong(1))).appendValue(result.getLong(2));
            } else {
                map.put(result.getLong(1), createParam(result));
            }
        }
        return map;
    }

    private synchronized ResultSet execute(long objectId, long attr_id, long classId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        getPreparedStatement().setLong(3, attr_id);
        return getPreparedStatement().executeQuery();
    }
}