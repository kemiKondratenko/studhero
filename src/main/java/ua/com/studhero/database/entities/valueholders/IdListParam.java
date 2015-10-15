package ua.com.studhero.database.entities.valueholders;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.List;

/**
 * Created by kaspyar on 9/19/15.
 */
public class IdListParam extends Param<List<Long>> implements ListParam {

    public static final long LISTPARAM = AttrTypes.List;

    public IdListParam(List<Long> value) {
        super(0, value);
    }

    public IdListParam(long param_id, List<Long> value) {
        super(param_id, value);
    }

    @Override
    public List<Long> get() {
        return super.get();
    }

    @Override
    public void appendValue(Long aLong) {
        get().add(aLong);
    }

    @Override
    public List<Long> difference(final List<Long> newValues) {
        return Lists.newArrayList(Iterables.filter(get(), new Predicate<Long>() {
            @Override
            public boolean apply(Long oldValue) {
                return ! newValues.contains(oldValue);
            }
        }));
    }

    @Override
    public List<Long> newValues(List<Long> newValues) {
        return Lists.newArrayList(Iterables.filter(newValues, new Predicate<Long>() {
            @Override
            public boolean apply(Long oldValue) {
                return ! get().contains(oldValue);
            }
        }));
    }
}
