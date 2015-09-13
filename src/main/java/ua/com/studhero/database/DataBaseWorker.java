package ua.com.studhero.database;

import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.exceptions.database.DuplicateLoginException;
import ua.com.studhero.exceptions.database.UnexpectedDBAnswerException;
import ua.com.studhero.model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface DataBaseWorker {

    public <T extends BaseDBO> long save(T value) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException;

    public <T extends BaseDBO> T get(long id, Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException;

    public <T extends BaseDBO> List<T> get(Class<T> objectClass) throws IllegalAccessException, InstantiationException, SQLException, ClassNotFoundException, NoSuchFieldException;

    public <T extends BaseDBO> boolean update(T object) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException;

    long getIdIfExists(User example) throws SQLException;

    long getPrimaryClassId(long id) throws SQLException;

    long createLoginable(String login, String password) throws SQLException, DuplicateLoginException, UnexpectedDBAnswerException;

    long save(long id, BaseDBO userInfo) throws SQLException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException;

    boolean isLoginValid(String login) throws SQLException;
}
