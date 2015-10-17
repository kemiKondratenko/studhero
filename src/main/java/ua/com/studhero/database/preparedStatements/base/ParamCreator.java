package ua.com.studhero.database.preparedStatements.base;

import com.google.common.collect.Lists;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.*;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eugene on 17.10.2015.
 */
public class ParamCreator extends MyPreparedStatement{

    public ParamCreator(PreparedStatement preparedStatement) {
        super(preparedStatement);
    }

    protected Param createParam(ResultSet result) throws SQLException {
        long type = result.getLong(4);
        long param_id = result.getInt(3);
        if(IntParam.INTPARAM == type)
            return new IntParam(param_id, result.getInt(2));
        if(StringParam.STRINGPARAM == type)
            return new StringParam(param_id, result.getString(2));
        if(TextParam.TEXTPARAM == type)
            return new TextParam(param_id, new TextValue(result.getLong(2)));
        if (IdListParam.LISTPARAM == type)
            return  new IdListParam(param_id, Lists.newArrayList(result.getLong(2)));
        if (DateParam.DATEPARAM == type)
            return  new DateParam(param_id, result.getString(2));
        if (TimeParam.TIMEPARAM == type)
            return  new TimeParam(param_id, result.getString(2));
        if (BooleanParam.BOOLEANPARAM == type)
            return  new BooleanParam(param_id, result.getBoolean(2));
        if (BaseDBOParam.BASEDBOPARAM == type)
            return  new BaseDBOParam(param_id, new BaseDBO(result.getLong(2)));
        if (BaseDBOListParam.BASEDBOLISTPARAM == type)
            return  new BaseDBOListParam(param_id,
                    Lists.newArrayList(new BaseDBO(result.getLong(2))));
        return null;
    }
}
