package ua.com.studhero.controllers.converters.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ua.com.studhero.controllers.converters.date.constants.DateFormat;
import ua.com.studhero.database.entities.valueholders.DateParam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eugene on 27.09.2015.
 */
public class DateSerializer  extends JsonSerializer<Date> {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.DEFAULT_DATE);

    @Override
    public void serialize(Date dateParam, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if(dateParam !=null)
            jsonGenerator.writeString(getFormatedDate(dateParam));
    }

    public static String getFormatedDate(Date date){
        return getSimpleDateFormat().format(date);
    }

    private static synchronized SimpleDateFormat getSimpleDateFormat(){
        return simpleDateFormat;
    }

}
