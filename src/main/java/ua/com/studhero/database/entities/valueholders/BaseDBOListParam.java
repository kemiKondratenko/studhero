package ua.com.studhero.database.entities.valueholders;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.base.ListParam;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.List;

/**
 * Created by Eugene on 15.10.2015.
 */
public class BaseDBOListParam extends Param<List<BaseDBO>> implements ListParam {

    public static final long BASEDBOLISTPARAM = AttrTypes.ListReference;

    public BaseDBOListParam(long param_id, List<BaseDBO> value) {
        super(param_id, value);
    }

    public BaseDBOListParam(List<BaseDBO> values) {
        super(0, values);
    }

    @Override
    public List<BaseDBO> get() {
        return super.get();
    }

    @Override
    public List<Long> getBaseForm() {
        return Lists.transform(get(), new Function<BaseDBO, Long>() {
            @Override
            public Long apply(BaseDBO baseDBO) {
                return baseDBO.getObjectId();
            }
        });
    }

    @Override
    public void appendValue(Long aLong) {
        get().add(new BaseDBO(aLong));
    }

    @Override
    public List<Long> difference(final List<Long> newValues) {
        return Lists.newArrayList(Iterables.filter(getBaseForm(), new Predicate<Long>() {
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
                return ! getBaseForm().contains(oldValue);
            }
        }));
    }
}
