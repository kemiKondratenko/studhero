package ua.com.studhero.model.entity;

import ua.com.studhero.database.entities.BaseDBO;

import java.util.*;

/**
 * @author kaspyar
 */
public class Event extends BaseDBO{
   //mandatory
    private int id;
    private Set<Tag> tags;
    private Company company;
    private String name;
    private GregorianCalendar calendar;
    private String photoUrl;
    private String descriptionShort;
    private String getDescriptionLong;
    private Status status;
    private int maxAmount;
    private int registered;

    //not mandatory
    private List<StudentUser> usersToCome;

    public Event() {
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
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
