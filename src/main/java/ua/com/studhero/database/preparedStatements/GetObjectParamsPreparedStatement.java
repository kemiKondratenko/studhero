package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.entities.valueholders.IntParam;
import ua.com.studhero.database.entities.valueholders.Param;
import ua.com.studhero.database.entities.valueholders.StringParam;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetObjectParamsPreparedStatement extends MyPreparedStatement {

    Logger log = Logger.getLogger("Logger");

    private static final String objectParamsQuery =  " SELECT attrs_id, param_value, attrs_type_id " +
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
            map.put(result.getLong(1), createParam(result));
        }
        return map;
    }

    private Param createParam(ResultSet result) throws SQLException {
        long type = result.getLong(3);
        if(IntParam.INTPARAM == type)
            return new IntParam(result.getInt(2));
        if(StringParam.STRINGPARAM == type)
            return new StringParam(result.getString(2));
        return null;
    }

    private synchronized  ResultSet execute(long objectId, long classId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        return getPreparedStatement().executeQuery();
    }
}
