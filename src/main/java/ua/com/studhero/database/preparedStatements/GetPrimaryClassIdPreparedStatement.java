package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.constants.RelationshipTypes;
import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 11.09.2015.
 */
public class GetPrimaryClassIdPreparedStatement extends MyPreparedStatement{
    private static final String getPrimaryClassIdPreparedStatement =
                    " SELECT class_id " +
                    " FROM objects_class_relationship" +
                    " WHERE object_id = ?" +
                    " AND type_id = "+ RelationshipTypes.primary;

    public GetPrimaryClassIdPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(getPrimaryClassIdPreparedStatement));
    }

    public long get(long id) throws SQLException {
        long classId = 0;
        ResultSet re = execute(id);
        while (re.next()){
            classId = re.getLong(1);
        }
        return classId;
    }

    private synchronized ResultSet execute(long id) throws SQLException {
        getPreparedStatement().setLong(1, id);
        return getPreparedStatement().executeQuery();
    }
}
