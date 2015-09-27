package ua.com.studhero.database;

import ua.com.studhero.database.entities.valueholders.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface QueryExecutor {
    Map<Long, Param> getObjectParams(long objectId, long classId) throws SQLException, ClassNotFoundException;

    Map<Long, Param> getObjectParam(long objectId, long attr_id, long classId) throws SQLException, ClassNotFoundException;

    boolean saveParameter(long paramId, Object value) throws SQLException;

    boolean createParameter(long objectId, long attrId, Object value, long classId) throws SQLException;

    void createParameter(long objectId, long attrId, List<Long> listValue, long classId) throws SQLException;
}
