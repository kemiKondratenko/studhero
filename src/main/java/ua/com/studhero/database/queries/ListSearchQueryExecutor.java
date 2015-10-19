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

    private static final String BASE_FIND =
                    " SELECT object_id" +
                    " FROM params as top" +
                    " WHERE ";

    private static final String PARAM_QUERY =
                    " EXISTS ( SELECT object_id" +
                            " FROM params " +
                            " WHERE object_id = top.object_id and " +
                            " attr_id = %d and " +
                            " param_value LIKE '%s' and " +
                            " class_id = %d )";

    private static final String TEXT_QUERY =
            " EXISTS ( SELECT p.object_id" +
                    " FROM params as p INNER JOIN texts as t on p.param_value = t.text_id" +
                    " WHERE object_id = top.object_id and " +
                    " attr_id = %d and " +
                    " class_id = %d and " +
                    " t.text LIKE '%s' )";

    private static final String AND =
            " AND ";

    private static final String OR =
            " OR ";

    private static final String GROUP_BY =
            " GROUP BY object_id ";


    public ListSearchQueryExecutor(Connection connection) {
        super(connection);
    }

    public List<Long> search(List<Long> paramAttrIds, List<Long> textAttrIds, String paramValue, long class_id) throws SQLException {
        StringBuilder query = new StringBuilder(BASE_FIND);
        String buildQueryParams = buildQueryParams(paramAttrIds, paramValue, class_id);
        String buildQueryTexts = buildQueryTexts(textAttrIds, paramValue, class_id);

        query.append(buildQueryParams);
        if(buildQueryParams != null && buildQueryTexts != null
                && !buildQueryParams.isEmpty() && !buildQueryTexts.isEmpty())
            query.append(OR);
        query.append(buildQueryTexts);
        query.append(GROUP_BY);

        log.info("ua.com.studhero.database.queries.ListSearchQueryExecutor.search @ 46"+ query.toString());
        ResultSet resultSet = execute(query.toString());
        List<Long> result = Lists.newArrayList();
        while (resultSet.next()){
            result.add(resultSet.getLong(1));
        }
        return result;
    }

    private String buildQueryParams(List<Long> paramAttrIds, String paramValue, long class_id) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < paramAttrIds.size(); i++){
            if(i != 0)
                builder.append(OR);
            builder.append(String.format(PARAM_QUERY, paramAttrIds.get(i), "%"+paramValue+"%", class_id));
        }
        return builder.toString();
    }

    private String buildQueryTexts(List<Long> textAttrIds, String paramValue, long class_id) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < textAttrIds.size(); i++){
            if(i != 0)
                builder.append(OR);
            builder.append(String.format(TEXT_QUERY, textAttrIds.get(i), class_id, "%"+paramValue+"%"));
        }
        return builder.toString();
    }
}
