package ua.com.studhero.model.entity;

import java.util.List;

/**
 * Created by kaspyar on 9/9/15.
 */
public class DaoMock {
    private List<Event> events;
    public List<Event> getAllEvents(){
        return events;
    }
    public void addEvent(Event event){
        events.add(event);
        return;
    }
    public void removeEvent(Event event){
        events.remove(event);
        return;
    }
//    public Eve updateEvent(Event event){
//        Event eventToMod = null;
//
//
//        return;
//    }
}
