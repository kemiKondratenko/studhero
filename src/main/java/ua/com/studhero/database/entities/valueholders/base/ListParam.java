package ua.com.studhero.database.entities.valueholders.base;

import java.util.List;

/**
 * Created by Eugene on 15.10.2015.
 */
public interface ListParam {
    void appendValue(Long aLong);

    List<Long> difference(List<Long> newValues);

    List<Long> newValues(List<Long> newValues);
}
