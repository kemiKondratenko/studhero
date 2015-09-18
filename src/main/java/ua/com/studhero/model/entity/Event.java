package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;

import java.util.*;

/**
 * @author kaspyar
 */
@ClassId(id = 9)
public class Event extends BaseDBO{

    @AttrId(id = 46)
    private List<Long> tags;
    @AttrId(id = 39)
    private long company;
    @AttrId(id = 34)
    private String name;
    private GregorianCalendar calendar;
    private String photoUrl;
    @AttrId(id = 40)
    private String descriptionShort;
    @AttrId(id = 41)
    private String getDescriptionLong;
    @AttrId(id = 35)
    private Status status;
    @AttrId(id = 42)
    private int maxAmount;
    @AttrId(id = 43)
    private int registered;

    //not mandatory
    private List<StudentUser> usersToCome;

    public Event() {
    }

    public Event(String message) {
        super(message);
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    public long getCompany() {
        return company;
    }

    public void setCompany(long company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getGetDescriptionLong() {
        return getDescriptionLong;
    }

    public void setGetDescriptionLong(String getDescriptionLong) {
        this.getDescriptionLong = getDescriptionLong;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getRegistered() {
        return registered;
    }

    public void setRegistered(int registered) {
        this.registered = registered;
    }

    public List<StudentUser> getUsersToCome() {
        return usersToCome;
    }

    public void setUsersToCome(List<StudentUser> usersToCome) {
        this.usersToCome = usersToCome;
    }
}
