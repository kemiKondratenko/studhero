package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.List;


/**
 * Created by Eugene on 15.10.2015.
 */
public class BaseDBOParam extends Param<BaseDBO> {

    public static final long BASEDBOPARAM = AttrTypes.Reference;

    public BaseDBOParam(long param_id, BaseDBO value) {
        super(param_id, value);
    }

    @Override
    public Long getBaseForm() {
        return get().getObjectId();
    }
}
