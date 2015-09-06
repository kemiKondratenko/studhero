package ua.com.studhero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.model.Event;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kaspyar on 9/6/15.
 */
@Controller
@RequestMapping("services")
public class RestController {

    @RequestMapping(value="/events", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> getShopInJSON() {
        List<Event> res = new ArrayList<Event>();
        Event event = null;
        for(int i=1;i<10;i++){
            event = new Event();
            event.setName(i+"");
            event.setStaffName(new String[]{"ala"+i, "hala"+i});
            res.add(event);
        }
        return res;

    }
}
