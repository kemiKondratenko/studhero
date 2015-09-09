package ua.com.studhero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.model.entity.DaoMock;
import ua.com.studhero.model.entity.Event;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kaspyar on 9/6/15.
 */
@Controller
@RequestMapping("services")
public class RestController {
    List<Event> events = new ArrayList<Event>();
    private DaoMock dao = new DaoMock();

    @RequestMapping(value="/events", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> getAllEvents() {
        return dao.getAllEvents();
    }

    @RequestMapping(value = "/event/create", method = RequestMethod.POST)
    public @ResponseBody Event createEmployee(@RequestBody Event event) {
        events.add(event);
        return event;
    }


}
