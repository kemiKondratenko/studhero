package ua.com.studhero.database;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.TextValue;
import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.exceptions.database.DataBaseConsistensyError;

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

    @Transactional(propagation = Propagation.NESTED)
    void createParameter(long objectId, long attrId, TextValue listParam, long classId) throws SQLException;

    void createParameter(long objectId, long attrId, List<Long> listValue, long classId) throws SQLException;

    long createNewObject(String name) throws SQLException;

    List<Long> getObjectsByClass(long classId, long primary) throws SQLException;

    long getObjectIdByUser(String email, String password) throws SQLException;

    long getPrimaryClassId(long id) throws SQLException;

    boolean createLoginable(long object_id, String login, String password) throws SQLException;

    void createObjectClassRelationship(long id, long classId, long primary) throws SQLException;

    boolean updateParameter(long id, long attr_id, Object baseForm, long classId) throws SQLException, ClassNotFoundException, DataBaseConsistensyError;

    boolean updateParameter(long id, Object baseForm) throws SQLException, DataBaseConsistensyError;

    boolean isLoginValid(String login) throws SQLException;

    List<Long> search(long paramAttrId, String paramValue) throws SQLException;

    List<Long> search(List<Long> paramAttrIds, List<Long> textAttrIds, String paramValue, long class_id) throws SQLException;

    List<Long> getPrimaryObjectsByClassLimitedFromTo(long class_id, long from, long to) throws SQLException;

    String getTestValue(Long id) throws SQLException;
}
