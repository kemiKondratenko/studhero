package ua.com.studhero.database.entities.valueholders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.studhero.controllers.converters.date.TimeDeserializer;
import ua.com.studhero.controllers.converters.date.TimeSerializer;
import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.Date;

/**
 * Created by Eugene on 27.09.2015.
 */

public class TimeParam  extends Param<Date> {

    public static final long TIMEPARAM = AttrTypes.Time;

    public TimeParam(Date date) {
        super(0, date);
    }

    public TimeParam(String date) {
        super(0, TimeDeserializer.getFormatedTime(date));
    }

    public TimeParam(long param_id, String date) {
        super(param_id, TimeDeserializer.getFormatedTime(date));
    }

    @Override
    public Date get() {
        return super.get();
    }

    @Override
    public String getBaseForm(){
        return TimeSerializer.getFormatedTime(get());
    }
}