package ua.com.studhero.controllers.entity;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.model.entity.Event;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 13.09.2015.
 */
@Controller
@RequestMapping("events")
public class EventsController {

    @Inject
    private DataBaseWorker dataBaseWorker;


    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> get(){
        try {
            return dataBaseWorker.get(Event.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Event create(@RequestBody Event entity) {
        try {
            return dataBaseWorker.get(dataBaseWorker.save(entity), entity.getClass());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
