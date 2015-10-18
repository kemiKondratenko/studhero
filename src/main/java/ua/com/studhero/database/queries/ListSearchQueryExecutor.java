package ua.com.studhero.database.queries;

import com.google.common.collect.Lists;
import ua.com.studhero.database.queries.base.BaseQueryExecutor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 18.10.2015.
 */
public class ListSearchQueryExecutor extends BaseQueryExecutor {

    Logger log = Logger.getLogger("Logger");

    private static final String BASEFIND =
                    " SELECT object_id" +
                    " FROM params as top" +
                    " WHERE ";

    private static final String BASEQUERY =
                    " EXISTS ( SELECT object_id" +
                            " FROM params " +
                            " WHERE object_id = top.object_id and " +
                            " attr_id = %d and " +
                            " param_value LIKE '%s' and " +
                            " class_id = %d )";

    private static final String AND =
            " AND ";

    private static final String GROUP_BY =
            " GROUP BY object_id ";


    public ListSearchQueryExecutor(Connection connection) {
        super(connection);
    }

    public List<Long> search(List<Long> paramAttrIds, String paramValue, long class_id) throws SQLException {
        StringBuilder querie = new StringBuilder(BASEFIND);
        querie.append(buildQuerie(paramAttrIds, paramValue, class_id));
        querie.append(GROUP_BY);
        log.info(querie.toString());
        ResultSet resultSet = execute(querie.toString());
        List<Long> result = Lists.newArrayList();
        while (resultSet.next()){
            result.add(resultSet.getLong(1));
        }
        return result;
    }

    private String buildQuerie(List<Long> paramAttrIds, String paramValue, long class_id) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < paramAttrIds.size(); i++){
            builder.append(String.format(BASEQUERY, paramAttrIds.get(i), "%"+paramValue+"%", class_id));
            if(i < paramAttrIds.size() - 1)
                builder.append(AND);
        }
        return builder.toString();
    }
}
