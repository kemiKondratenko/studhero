package ua.com.studhero.database;

import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;
import ua.com.studhero.exceptions.database.DataBaseConsistensyError;
import ua.com.studhero.exceptions.database.DuplicateLoginException;
import ua.com.studhero.exceptions.database.UnexpectedDBAnswerException;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface DataBaseWorker {

    public <T extends BaseDBO> long save(T value) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, DataBaseConsistensyError;

    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException;

    public <T extends BaseDBO> T getFull(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException;

    public BaseDBO get(Long id) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    public BaseDBO getFull(Long id) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    public <T extends BaseDBO> List<T> get(Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException;

    public <T extends BaseDBO> boolean update(T object) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException, DataBaseConsistensyError;

    public long getIdIfExists(User example) throws SQLException;

    public long getPrimaryClassId(long id) throws SQLException;

    public long createLoginable(String login, String password) throws SQLException, DuplicateLoginException, UnexpectedDBAnswerException;

    public long save(long id, BaseDBO userInfo) throws SQLException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, DataBaseConsistensyError;

    public boolean isLoginValid(String login) throws SQLException;

    public <T extends BaseDBO>  List<T> get(Class<T> classValue, Long from, Long to) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    List<Long> search(SearchScope searchScope) throws SQLException;

    List<Long> search(SearchScope searchScope, long id) throws SQLException;
}
