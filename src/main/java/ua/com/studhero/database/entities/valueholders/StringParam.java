package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;

/**
 * Created by Eugene on 10.09.2015.
 */
public class StringParam extends Param<String> {

    public static final long STRINGPARAM = AttrTypes.String;

    public StringParam(String anInt) {
        super(anInt);
    }

    @Override
    public String get() {
        return super.get();
    }
}
