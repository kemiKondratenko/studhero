package ua.com.studhero.database;

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

    boolean createParameter(long objectId, long attrId, Object value, long classId) throws SQLException;

    void createParameter(long objectId, long attrId, List<Long> listValue, long classId) throws SQLException;

    long createNewObject(String name) throws SQLException;

    List<Long> getObjectsByClass(long classId, long primary) throws SQLException;

    long getObjectIdByUser(String email, String password) throws SQLException;

    long getPrimaryClassId(long id) throws SQLException;

    boolean createLoginable(long object_id, String login, String password) throws SQLException;

    void createObjectClassRelationship(long id, long classId, long primary) throws SQLException;

    boolean updateParameter(long id, long attr_id, Object baseForm, long classId) throws SQLException, ClassNotFoundException;

    boolean updateParameter(long id, Object baseForm) throws SQLException;

    boolean isLoginValid(String login) throws SQLException;

    List<Long> search(long paramAttrId, String paramValue) throws SQLException;
}
