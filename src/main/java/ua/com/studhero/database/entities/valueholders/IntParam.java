package ua.com.studhero.database.entities.valueholders;

/**
 * Created by Eugene on 09.09.2015.
 */
public class IntParam extends Param<Integer> {

    public static final String INTPARAM = "Integer";

    public IntParam(int anInt) {
        super(anInt);
    }

    @Override
    public Integer get() {
        return super.get();
    }
}
