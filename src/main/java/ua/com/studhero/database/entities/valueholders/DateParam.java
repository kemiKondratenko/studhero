package ua.com.studhero.database.entities.valueholders;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.studhero.controllers.converters.date.DateDeserializer;
import ua.com.studhero.controllers.converters.date.DateSerializer;
import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.Param;

import java.util.Date;

/**
 * Created by Eugene on 27.09.2015.
 */
public class DateParam  extends Param<Date> {

    public static final long DATEPARAM = AttrTypes.Date;

    public DateParam(Date date) {
        super(0, date);
    }

    public DateParam(String date) {
        super(0, DateDeserializer.getFormatedDate(date));
    }

    public DateParam(long param_id, String date) {
        super(param_id, DateDeserializer.getFormatedDate(date));
    }

    @Override
    public Date get() {
        return super.get();
    }

    @Override
    public String getBaseForm(){
        return DateSerializer.getFormatedDate(get());
    }
}
