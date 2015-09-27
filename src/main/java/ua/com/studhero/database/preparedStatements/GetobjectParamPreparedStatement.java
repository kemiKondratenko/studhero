package ua.com.studhero.database.preparedStatements;

import com.google.common.collect.Lists;
import ua.com.studhero.database.entities.valueholders.*;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Eugene on 27.09.2015.
 */
public class GetobjectParamPreparedStatement extends MyPreparedStatement {

    Logger log = Logger.getLogger("Logger");

    private static final String objectParamQuery = " SELECT attrs_id, param_value, param_id, attrs_type_id " +
            " FROM attrs INNER JOIN params on attrs.attrs_id = params.attr_id " +
            " WHERE object_id = ? " +
            " AND class_id = ? " +
            " AND attr_id = ? ";

    public GetobjectParamPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(objectParamQuery));
    }

    public Map<Long, Param> getObjectParams(long objectId, long attr_id, long classId) throws SQLException, ClassNotFoundException {
        Map<Long, Param> map = new HashMap<Long, Param>();
        ResultSet result = execute(objectId, attr_id, classId);
        while (result.next()) {
            if (map.containsKey(result.getLong(1))) {
                map.put(result.getLong(1), appendParam(result, ((ListParam) map.get(result.getLong(1)))));
            } else {
                map.put(result.getLong(1), createParam(result));
            }
        }
        return map;
    }

    private Param appendParam(ResultSet resultSet, ListParam list) throws SQLException {
        list.appendValue(resultSet.getLong(2));
        return list;
    }

    private Param createParam(ResultSet result) throws SQLException {
        long type = result.getLong(4);
        long param_id = result.getInt(3);
        if (IntParam.INTPARAM == type)
            return new IntParam(param_id, result.getInt(2));
        if (StringParam.STRINGPARAM == type)
            return new StringParam(param_id, result.getString(2));
        if (ListParam.LISTPARAM == type)
            return new ListParam(param_id, Lists.newArrayList(result.getLong(2)));
        if (DateParam.DATEPARAM == type)
            return new DateParam(param_id, result.getString(2));
        if (TimeParam.TIMEPARAM == type)
            return new TimeParam(param_id, result.getString(2));
        return null;
    }

    private synchronized ResultSet execute(long objectId, long attr_id, long classId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        getPreparedStatement().setLong(3, attr_id);
        return getPreparedStatement().executeQuery();
    }
}