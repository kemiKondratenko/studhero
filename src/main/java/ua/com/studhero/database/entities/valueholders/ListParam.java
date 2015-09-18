package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.List;

/**
 * Created by kaspyar on 9/19/15.
 */
public class ListParam extends Param<List<Long>> {
    public static final long LISTPARAM = AttrTypes.List;
    public ListParam(long param_id, List<Long> value) {
        super(param_id, value);
    }
    @Override
    public List<Long> get() {
        return super.get();
    }

    public void appendValue(Long aLong) {
        get().add(aLong);
    }
}
