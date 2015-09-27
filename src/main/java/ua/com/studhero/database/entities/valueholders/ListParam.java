package ua.com.studhero.database.entities.valueholders;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.List;

/**
 * Created by kaspyar on 9/19/15.
 */
public class ListParam extends Param<List<Long>> {

    public static final long LISTPARAM = AttrTypes.List;

    public ListParam(List<Long> value) {
        super(0, value);
    }

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

    public List<Long> difference(final List<Long> newValues) {
        return Lists.newArrayList(Iterables.filter(get(), new Predicate<Long>() {
            @Override
            public boolean apply(Long oldValue) {
                return ! newValues.contains(oldValue);
            }
        }));
    }

    public List<Long> newValues(List<Long> newValues) {
        return Lists.newArrayList(Iterables.filter(newValues, new Predicate<Long>() {
            @Override
            public boolean apply(Long oldValue) {
                return ! get().contains(oldValue);
            }
        }));
    }
}
