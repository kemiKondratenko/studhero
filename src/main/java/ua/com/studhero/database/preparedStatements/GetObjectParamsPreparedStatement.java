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


public class GetObjectParamsPreparedStatement  extends ParamCreator {

    Logger log = Logger.getLogger("Logger");

    private static final String objectParamsQuery =  " SELECT attrs_id, param_value, param_id, attrs_type_id " +
                                                     " FROM attrs INNER JOIN params on attrs.attrs_id = params.attr_id " +
                                                     " WHERE object_id = ? " +
                                                     " AND class_id = ? ";

    public GetObjectParamsPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(objectParamsQuery));
    }

    public Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException {
        Map<Long, Param> map = new HashMap<Long, Param>();
        ResultSet result = execute(objectId, classId);
        while (result.next()) {
            if (map.containsKey(result.getLong(1))){
                ((ListParam) map.get(result.getLong(1))).appendValue(result.getLong(2));
            } else {
                map.put(result.getLong(1), createParam(result));
            }
        }
        return map;
    }

    private synchronized  ResultSet execute(long objectId, long classId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        return getPreparedStatement().executeQuery();
    }
}
