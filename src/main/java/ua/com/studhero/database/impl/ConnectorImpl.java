package ua.com.studhero.database.impl;

import ua.com.studhero.database.DataBaseConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Eugene on 06.09.2015.
 */
public class ConnectorImpl {

    private Connection connection = null;
    private final String CONNECTIONLINK = "jdbc:mysql://localhost:3306/";

    private String dfjkdfkl() throws SQLException {
        String res = "";
        res+= "-------- MySQL JDBC Connection Testing ------------";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return "Where is your MySQL JDBC Driver?";
        }

        res+= "MySQL JDBC Driver Registered!";
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(CONNECTIONLINK+DataBaseConstants.DATABASE_NAME, DataBaseConstants.DATABASE_USER_NAME, DataBaseConstants.DATABASE_USER_PASSWORD);

        } catch (SQLException e) {
            res+= "Connection Failed! Check output console";
            return res;
        }

        if (connection != null) {
            res+= "You made it, take control your database now!";
            connection.close();
        } else {
            res+= "Failed to make connection!";
        }
        return res;
    }
}
