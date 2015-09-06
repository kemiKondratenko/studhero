package ua.com.studhero.database.preparedStatements;

import java.sql.PreparedStatement;

/**
 * Created by Eugene on 06.09.2015.
 */
public class MyPreparedStatement {
    private PreparedStatement preparedStatement;

    public MyPreparedStatement(PreparedStatement preparedStatement){
        this.preparedStatement = preparedStatement;
    }
}
