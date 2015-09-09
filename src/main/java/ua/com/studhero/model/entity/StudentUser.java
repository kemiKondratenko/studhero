package ua.com.studhero.model.entity;

import ua.com.studhero.database.entities.BaseDBO;

import java.util.List;
import java.util.Set;

/**
 * @author KaspYar
 */
public class StudentUser extends BaseDBO {
    //mandatory
    private String firstName;
    private String lastName;
    private String username;
    // not mandatory
    private String city;
    private Set<Tag> interests;
    private List<Event> events;

    public StudentUser() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Tag> getInterests() {
        return interests;
    }

    public void setInterests(Set<Tag> interests) {
        this.interests = interests;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
