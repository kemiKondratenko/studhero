package ua.com.studhero.database.preparedStatements;

import com.google.common.collect.Lists;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.*;
import ua.com.studhero.database.entities.valueholders.base.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetObjectParamsPreparedStatement extends MyPreparedStatement {

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

    private Param createParam(ResultSet result) throws SQLException {
        long type = result.getLong(4);
        long param_id = result.getInt(3);
        if(IntParam.INTPARAM == type)
            return new IntParam(param_id, result.getInt(2));
        if(StringParam.STRINGPARAM == type)
            return new StringParam(param_id, result.getString(2));
        if (IdListParam.LISTPARAM == type)
            return  new IdListParam(param_id, Lists.newArrayList(result.getLong(2)));
        if (DateParam.DATEPARAM == type)
            return  new DateParam(param_id, result.getString(2));
        if (TimeParam.TIMEPARAM == type)
            return  new TimeParam(param_id, result.getString(2));
        if (BooleanParam.BOOLEANPARAM == type)
            return  new BooleanParam(param_id, result.getBoolean(2));
        if (BaseDBOParam.BASEDBOPARAM == type)
            return  new BaseDBOParam(param_id, new BaseDBO(result.getLong(2)));
        if (BaseDBOListParam.BASEDBOLISTPARAM == type)
            return  new BaseDBOListParam(param_id,
                    Lists.newArrayList(new BaseDBO(result.getLong(2))));
        return null;
    }

    private synchronized  ResultSet execute(long objectId, long classId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        return getPreparedStatement().executeQuery();
    }
}
