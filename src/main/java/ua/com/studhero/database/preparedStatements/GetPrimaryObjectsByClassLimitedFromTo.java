package ua.com.studhero.database.preparedStatements;

import com.google.common.collect.Lists;
import ua.com.studhero.database.constants.RelationshipTypes;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 29.09.2015.
 */
public class GetPrimaryObjectsByClassLimitedFromTo extends MyPreparedStatement{
    private static final String getObjectsByClassLimitedFromTO =
                    " SELECT object_id" +
                    " FROM objects_class_relationship" +
                    " WHERE class_id = ?" +
                    " AND type_id = "+ RelationshipTypes.primary +
                    " LIMIT ? , ?";

    public GetPrimaryObjectsByClassLimitedFromTo(Connection connection) throws SQLException {
        super(connection.prepareStatement(getObjectsByClassLimitedFromTO));
    }

    public List<Long> get(long class_id, long from, long to) throws SQLException {
        List<Long> res = Lists.newArrayList();
        ResultSet resultSet = execute(class_id, from, to);
        while (resultSet.next()){
            res.add(resultSet.getLong(1));
        }
        return res;
    }

    private ResultSet execute(long class_id, long from, long to) throws SQLException {
        getPreparedStatement().setLong(1, class_id);
        getPreparedStatement().setLong(2, from);
        getPreparedStatement().setLong(3, to);
        return getPreparedStatement().executeQuery();
    }
}
