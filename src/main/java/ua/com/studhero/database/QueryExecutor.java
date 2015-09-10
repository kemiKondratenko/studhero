package ua.com.studhero.database;

import ua.com.studhero.database.entities.valueholders.Param;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface QueryExecutor {
    Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException;

    boolean saveParameter(long paramId, Object value) throws SQLException;

    boolean createParameter(long objectId, long attrId, Object value, long classId) throws SQLException;
}
