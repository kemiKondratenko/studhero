package ua.com.studhero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.model.Event;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kaspyar on 9/6/15.
 */
@Controller
@RequestMapping("services")
public class RestController {
    List<Event> events = new ArrayList<Event>();

    @RequestMapping(value="/events", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> getShopInJSON() {
        Event event = new Event();
        event.setName("First");
        String [] staff = new String []{"al1", "al2"};

        event.setStaffName(staff);
        events.add(event);
        return events;
    }

    @RequestMapping(value = "/event/create", method = RequestMethod.POST)
    public @ResponseBody Event createEmployee(@RequestBody Event event) {
        events.add(event);
        return event;
    }
//
//    @RequestMapping(value = "/events/delete", method = RequestMethod.PUT)
//    public @ResponseBody Event deleteEmployee(@PathVariable("name") String empName) {
//        Event emp = null;
//        for(int i=0;i<events.size();i++){
//            if(events.get(i).getName().equals(empName)){
//                emp = events.get(i);
//                break;
//            }
//        }
//        events.remove(emp);
//
//        return emp;
//    }

}
