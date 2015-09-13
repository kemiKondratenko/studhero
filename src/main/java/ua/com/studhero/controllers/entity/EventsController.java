package ua.com.studhero.controllers.entity;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.model.entity.Event;

import javax.inject.Inject;
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
        }  catch (Exception e) {
            return Lists.newArrayList(new Event(e.getMessage()));
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Event getById(@PathVariable long id){
        try {
            return dataBaseWorker.get(id, Event.class);
        }  catch (Exception e) {
            return new Event(e.getMessage());
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Event create(@RequestBody Event entity) {
        try {
            return dataBaseWorker.get(dataBaseWorker.save(entity.getObjectId(), entity), entity.getClass());
        }  catch (Exception e) {
            return new Event(e.getMessage());
        }
    }

}
