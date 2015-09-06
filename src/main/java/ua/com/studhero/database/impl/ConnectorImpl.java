package ua.com.studhero.database.impl;

import ua.com.studhero.database.Connector;
import ua.com.studhero.database.DataBaseConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Eugene on 06.09.2015.
 */
public class ConnectorImpl implements Connector{

    private Connection connection = null;

    public ConnectorImpl(String s, String s2, String s23) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection(s, s2, s23);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String dfjkdfkl() throws SQLException {
        String res = "";
        res += "-------- MySQL JDBC Connection Testing ------------";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return "Where is your MySQL JDBC Driver?";
        }

        res += "MySQL JDBC Driver Registered!";
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/" + DataBaseConstants.DATABASE_NAME, DataBaseConstants.DATABASE_USER_NAME, DataBaseConstants.DATABASE_USER_PASSWORD);

        } catch (SQLException e) {
            res += "Connection Failed! Check output console";
            return res;
        }

        if (connection != null) {
            res += "You made it, take control your database now!";
            connection.close();
        } else {
            res += "Failed to make connection!";
        }
        return res;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
