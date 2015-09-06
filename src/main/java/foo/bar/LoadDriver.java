package foo.bar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class LoadDriver {
    public LoadDriver() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }

    public String dfjkdfkl() throws SQLException {
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
                    .getConnection("jdbc:mysql://localhost:3306/studhero","root", "SmmngpRRR4543gfdDDlfg");

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
        connection.
        return res;
    }
}