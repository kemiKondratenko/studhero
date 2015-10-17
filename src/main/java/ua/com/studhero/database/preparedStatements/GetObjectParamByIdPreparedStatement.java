package ua.com.studhero.database.preparedStatements;

import ua.com.studhero.database.entities.valueholders.base.Param;
import ua.com.studhero.database.preparedStatements.base.ParamCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 17.10.2015.
 */
public class GetObjectParamByIdPreparedStatement  extends ParamCreator {
    private static final String getObjectParamByIdPreparedStatement =
                    " SELECT attrs_id, param_value, param_id, attrs_type_id " +
                    " FROM attrs INNER JOIN params on attrs.attrs_id = params.attr_id " +
                    " WHERE param_id = ?";

    public GetObjectParamByIdPreparedStatement(Connection connection) throws SQLException {
        super(connection.prepareStatement(getObjectParamByIdPreparedStatement));
    }

    public Param get(Long id) throws SQLException {
        ResultSet resultSet = execute(id);
        while (resultSet.next()){
            return createParam(resultSet);
        }
        return null;
    }

    private ResultSet execute(Long id) throws SQLException {
        getPreparedStatement().setLong(1, id);
        return getPreparedStatement().executeQuery();
    }
}
