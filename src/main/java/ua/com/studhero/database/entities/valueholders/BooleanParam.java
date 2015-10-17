package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.constants.AttrValues;
import ua.com.studhero.database.entities.valueholders.base.Param;

/**
 * Created by Eugene on 27.09.2015.
 */
public class BooleanParam extends Param<Boolean>{

    public static final long BOOLEANPARAM = AttrTypes.Boolean;
    public static final long TRUE = AttrValues.TRUE;
    public static final long FALSE = AttrValues.FALSE;

    public BooleanParam(Boolean approved) {
        super(0, approved);
    }

    public BooleanParam(long param_id, Boolean value) {
        super(param_id, value);
    }

    public Boolean get(){
        return super.get();
    }

}
