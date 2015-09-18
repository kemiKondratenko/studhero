package ua.com.studhero.database.constants;

/**
 * Created by Eugene on 19.09.2015.
 */
public class AttrIdFactory {

    public static long getAttrId(String paramName) {
        if(Attrs.NAME.equalsIgnoreCase(paramName)) return 34;
        if(Attrs.EMAIL.equalsIgnoreCase(paramName)) return 32;
        if(Attrs.PASSWORD.equalsIgnoreCase(paramName)) return 33;
        if(Attrs.STATUS.equalsIgnoreCase(paramName)) return 35;
        if(Attrs.CITY.equalsIgnoreCase(paramName)) return 38;
        if(Attrs.DESCRIPTION_SHORT.equalsIgnoreCase(paramName)) return 40;
        if(Attrs.DESCRIPTION_LONG.equalsIgnoreCase(paramName)) return 41;
        if(Attrs.TAG_NAME.equalsIgnoreCase(paramName)) return 45;
        if(Attrs.MAX_AMOUNT.equalsIgnoreCase(paramName)) return 42;
        if(Attrs.REGISTERED.equalsIgnoreCase(paramName)) return 43;
        return 0;
    }

}
