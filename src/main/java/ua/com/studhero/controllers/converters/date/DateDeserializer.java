package ua.com.studhero.controllers.converters.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ua.com.studhero.controllers.converters.date.constants.DateFormat;
import ua.com.studhero.database.entities.valueholders.DateParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Eugene on 27.09.2015.
 */
public class DateDeserializer  extends JsonDeserializer<Date> {

    private static Logger log = Logger.getLogger("Logger");

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.DEFAULT_DATE);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if(jsonParser == null)
            return null;
        String value = jsonParser.getText();
        if(value != null)
            return getFormatedDate(value);
        else
            return null;
    }

    public static Date getFormatedDate(String date){
        try {
            log.info("date string "+date);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static synchronized SimpleDateFormat getSimpleDateFormat(){
        return simpleDateFormat;
    }
}
