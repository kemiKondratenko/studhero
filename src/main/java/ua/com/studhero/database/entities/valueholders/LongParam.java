package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.entities.valueholders.base.Param;

/**
 * Created by Eugene on 27.09.2015.
 */
public class LongParam extends Param<Long>{

    public LongParam(Long value) {
        super(0, value);
    }

    public LongParam(long param_id, Long value) {
        super(param_id, value);
    }


    public Long get(){
        return super.get();
    }

}
