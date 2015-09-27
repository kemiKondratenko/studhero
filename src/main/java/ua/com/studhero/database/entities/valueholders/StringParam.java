package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.Param;

/**
 * Created by Eugene on 10.09.2015.
 */public class StringParam extends Param<String> {

    public static final long STRINGPARAM = AttrTypes.String;

    public StringParam(String anInt) {
        super(0, anInt);
    }

    public StringParam(long param_id, String anInt) {
        super(param_id, anInt);
    }

    @Override
    public String get() {
        return super.get();
    }
}
