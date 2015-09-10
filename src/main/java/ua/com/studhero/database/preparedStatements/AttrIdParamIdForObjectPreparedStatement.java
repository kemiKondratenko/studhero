package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 09.09.2015.
 */
public class AttrIdParamIdForObjectPreparedStatement extends MyPreparedStatement {

    private static final String attrIdParamIdForObjectPreparedStatement =
                    " SELECT  attrs_id, param_id " +
                    " FROM attrs INNER JOIN params on attrs.attrs_id = params.param_id " +
                    " WHERE attrs.class_id = ? AND params.object_id = ?";

    public AttrIdParamIdForObjectPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(attrIdParamIdForObjectPreparedStatement));
    }

    public Map<Long, Long> get(long objectId, long id) throws SQLException {
        Map<Long, Long> result = new HashMap<Long, Long>();
        ResultSet res = execute(objectId, id);
        while (res.next()){
            result.put(res.getLong(1), res.getLong(2));
        }
        return result;
    }

    private synchronized ResultSet execute(long objectId, long id) throws SQLException {
        getPreparedStatement().setLong(1, id);
        getPreparedStatement().setLong(2, objectId);
        return  getPreparedStatement().executeQuery();
    }
}
