package ua.com.studhero.controllers.converters.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ua.com.studhero.controllers.converters.date.constants.DateFormat;
import ua.com.studhero.database.entities.valueholders.TimeParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eugene on 27.09.2015.
 */
public class TimeDeserializer extends JsonDeserializer<Date> {

    private static SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(DateFormat.DEFAULT_TIME);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return getFormatedTime(jsonParser.getText());
    }

    public static Date getFormatedTime(String time){
        try {
            return getSimpleTimeFormat().parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static synchronized SimpleDateFormat getSimpleTimeFormat(){
        return simpleTimeFormat;
    }
}
