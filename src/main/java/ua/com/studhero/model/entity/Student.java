package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.FullLoad;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.BaseDBOListParam;
import ua.com.studhero.database.entities.valueholders.StringParam;

import java.util.List;

/**
 * @author KaspYar
 */
@ClassId(id = 7)
public class Student extends BaseDBO {

    @AttrId(id = 34)
    private StringParam name;
    @AttrId(id = 37)
    private StringParam lastName;
    //redundant
    @AttrId(id = 32)
    private StringParam email;
    @AttrId(id = 64)
    private StringParam phone;
    @AttrId(id = 38)
    private StringParam city;

    @FullLoad
    @AttrId(id = 73)
    private BaseDBOListParam events;

    public Student() {
    }

    public Student(String message) {
        setError(message);
    }

    public String getName() {
        if(name != null)
            return name.get();
        return null;
    }

    public void setName(String name) {
        if(this.name == null && name !=null)
            this.name = new StringParam(name);
        this.name.set(name);
    }

    public String getLastName() {
        if(lastName != null)
            return lastName.get();
        return null;
    }

    public void setLastName(String lastName) {
        if(this.lastName == null && lastName !=null)
            this.lastName = new StringParam(lastName);
        this.lastName.set(lastName);
    }

    public String getEmail() {
        if(email != null)
            return email.get();
        return null;
    }

    public void setEmail(String email) {
        if(this.email == null && email !=null)
            this.email = new StringParam(email);
        this.email.set(email);
    }

    public String getPhone() {
        if(phone != null)
            return phone.get();
        return null;
    }

    public void setPhone(String phone) {
        if(this.phone == null && phone !=null)
            this.phone = new StringParam(phone);
        this.phone.set(phone);
    }

    public String getCity() {
        if(city != null)
            return city.get();
        return null;
    }

    public void setCity(String city) {
        if(this.city == null && city !=null)
            this.city = new StringParam(city);
        this.city.set(city);
    }

    public List<BaseDBO> getEvents() {
        if(events != null)
            return events.get();
        return null;
    }

    public void setEvents(List<BaseDBO> events) {
        if(this.events == null && events !=null)
            this.events = new BaseDBOListParam(events);
    }
}
