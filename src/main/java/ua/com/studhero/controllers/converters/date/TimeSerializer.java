package ua.com.studhero.controllers.converters.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ua.com.studhero.controllers.converters.date.constants.DateFormat;
import ua.com.studhero.database.entities.valueholders.TimeParam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eugene on 27.09.2015.
 */
public class TimeSerializer   extends JsonSerializer<Date> {

    private static SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(DateFormat.DEFAULT_TIME);

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if(date != null)
            jsonGenerator.writeString(getFormatedTime(date));
    }

    public static String getFormatedTime(Date date){
        return getSimpleTimeFormat().format(date);
    }

    private static synchronized SimpleDateFormat getSimpleTimeFormat(){
        return simpleTimeFormat;
    }
}
