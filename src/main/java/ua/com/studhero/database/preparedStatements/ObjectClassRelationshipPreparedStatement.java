package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.preparedStatements.base.MyPreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugene on 10.09.2015.
 */
public class ObjectClassRelationshipPreparedStatement extends MyPreparedStatement{
    private static final String objectClassRelationshipPreparedStatement =
            "INSERT INTO objects_class_relationship (relationship_id, object_id, class_id, type_id)" +
                    "VALUES (0, ?, ?, ?)";

    public ObjectClassRelationshipPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(objectClassRelationshipPreparedStatement));
    }

    public void create(long objectId, long classId, long typeId) throws SQLException {
        execute(objectId, classId, typeId);
    }

    private synchronized void execute(long objectId, long classId, long typeId) throws SQLException {
        getPreparedStatement().setLong(1, objectId);
        getPreparedStatement().setLong(2, classId);
        getPreparedStatement().setLong(3, typeId);
        getPreparedStatement().execute();
    }
}
