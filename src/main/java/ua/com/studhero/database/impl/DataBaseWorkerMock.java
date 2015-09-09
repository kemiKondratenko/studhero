package ua.com.studhero.database.impl;

import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.model.entity.Tag;
import ua.com.studhero.model.entity.User;

import java.util.*;

/**
 * Created by kaspyar on 9/9/15.
 */
public class DataBaseWorkerMock {
    private List<User> users = new ArrayList<User>();
    private List<Event> events = new ArrayList<Event>();
    private List<Company> companies = new ArrayList<Company>();

    public DataBaseWorkerMock() {
        Company company = new Company();
        company.setName("Company1");
        company.setObjectId(1l);
        company.setStatus("Approved");

        Set<Tag> tags1 = new HashSet<Tag>();
        tags1.add(Tag.IT);
        tags1.add(Tag.MARKETING);

        Set<Tag> tags2 = new HashSet<Tag>();
        tags2.add(Tag.SALES);
        tags1.add(Tag.MARKETING);


        Event event = new Event();
        event.setObjectId(2l);
        event.setName("Event1");
        event.setCalendar(new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));
        event.setCompany(company);
        event.setDescriptionShort("Short description Event1");
        event.setGetDescriptionLong("Long description Event1");
        event.setMaxAmount(100);
        event.setPhotoUrl("photo url Event1");
        event.setTags(tags1);

        Event event1 = new Event();
        event1.setObjectId(3l);
        event1.setName("Event2");
        event1.setCalendar(new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));
        event1.setCompany(company);
        event1.setDescriptionShort("Short description Event2");
        event1.setGetDescriptionLong("Long description Event2");
        event1.setMaxAmount(20);
        event1.setPhotoUrl("photo url Event2");
        event1.setTags(tags1);

        events.add(event);
        events.add(event1);
        companies.add(company);
    }

    public List<Event> getAllEvents(){
        return events;
    }
    public void addEvent(Event event){
        events.add(event);
        return;
    }

    public List<Company> getAllCompanies() {
        return companies;
    }

    public String removeEvent(Long eventId) {
        String result = "ERROR";
        for(int i=0;i<events.size();i++){
            if (events.get(i).getObjectId() == eventId){

                events.remove(i);
                result = "SUCCESS";
            }
        }
        return result;
    }
}
