package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.Param;

/**
 * Created by Eugene on 09.09.2015.
 */
public class IntParam extends Param<Integer> {

    public static final long INTPARAM = AttrTypes.Integer;

    public IntParam(long param_id, int anInt) {
        super(param_id, anInt);
    }

    @Override
    public Integer get() {
        return super.get();
    }
}
