package ua.com.studhero.database.preparedStatements;

import com.google.common.collect.Lists;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 19.09.2015.
 */
public class SimpleSearchPreparedStatement extends MyPreparedStatement{
    private static final String simpleSearchPreparedStatement =
                                                            " SELECT object_id" +
                                                            " FROM params" +
                                                            " WHERE attr_id = ? AND param_value = ?";

    public SimpleSearchPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(simpleSearchPreparedStatement));
    }

    public List<Long> search(long paramAttrId, String paramValue) throws SQLException {
        ResultSet resultSet = execute(paramAttrId, paramValue);
        List<Long> result = Lists.newArrayList();
        while (resultSet.next()){
            result.add(resultSet.getLong(1));
        }
        return result;
    }

    private synchronized ResultSet execute(long paramAttrId, String paramValue) throws SQLException {
        getPreparedStatement().setLong(1, paramAttrId);
        getPreparedStatement().setObject(2, paramValue);
        return getPreparedStatement().executeQuery();
    }
}
