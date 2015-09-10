package ua.com.studhero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.Example;
import ua.com.studhero.database.impl.DataBaseWorkerMock;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.model.entity.User;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 08.09.2015.
 */
@Controller
@RequestMapping("db")
public class RestDBController {

    Logger log = Logger.getLogger("Logger");

    @Inject
    private DataBaseWorker dataBaseWorker;
    private DataBaseWorkerMock dataBaseWorkerMock = new DataBaseWorkerMock();

    @RequestMapping(value="/events/getEvents", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> getFromDB(){
        return dataBaseWorkerMock.getAllEvents();
    }

    @RequestMapping(value="/saveToDB", method = RequestMethod.GET)
    public @ResponseBody
    User saveToDB(){
        try {
            User example = new User();
            example.setEmail("mySuperMail@gmail");
            example.setPassword("MysecretPass");
            return dataBaseWorker.get(dataBaseWorker.save(example), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/companies", method = RequestMethod.GET)
    public @ResponseBody
    List<Company> getCompaniesFromDB(){
        return dataBaseWorkerMock.getAllCompanies();
    }

    @RequestMapping(value = "/events/createEvent", method = RequestMethod.POST)
    public @ResponseBody Event createEvent(@RequestBody Event event) {
        dataBaseWorkerMock.addEvent(event);
        return event;
    }


    @RequestMapping(value = "/events/deleteEvent", method = RequestMethod.POST)
    public @ResponseBody String createEmployee(@RequestBody Long eventId) {
        return dataBaseWorkerMock.removeEvent(eventId);
    }
    
    public void setDataBaseWorker(DataBaseWorker dataBaseWorker) {
        this.dataBaseWorker = dataBaseWorker;
    }
}
