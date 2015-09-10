package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;

/**
 * Created by Eugene on 09.09.2015.
 */
public class IntParam extends Param<Integer> {

    public static final long INTPARAM = AttrTypes.Integer;

    public IntParam(int anInt) {
        super(anInt);
    }

    @Override
    public Integer get() {
        return super.get();
    }
}
