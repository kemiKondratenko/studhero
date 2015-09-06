package ua.com.studhero.database;

import java.sql.Connection;

/**
 * Created by Eugene on 06.09.2015.
 */
public interface Connector {
    Connection getConnection();
}
