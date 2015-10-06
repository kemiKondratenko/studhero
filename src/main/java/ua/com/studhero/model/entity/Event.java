package ua.com.studhero.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.controllers.converters.date.DateDeserializer;
import ua.com.studhero.controllers.converters.date.DateSerializer;
import ua.com.studhero.controllers.converters.date.TimeDeserializer;
import ua.com.studhero.controllers.converters.date.TimeSerializer;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.*;

import java.util.*;

/**
 * @author kaspyar
 */
@ClassId(id = 9)
public class Event extends BaseDBO{

    @AttrId(id = 46)
    private ListParam tags;
    @AttrId(id = 39)
    private LongParam company;
    @AttrId(id = 34)
    private StringParam title;
    @AttrId(id = 40)
    private StringParam descriptionShort;
    @AttrId(id = 41)
    private StringParam description;
    @AttrId(id = 59)
    private DateParam startDate;
    @AttrId(id = 60)
    private DateParam endDate;
    @AttrId(id = 61)
    private TimeParam startTime;
    @AttrId(id = 62)
    private TimeParam endTime;
    @AttrId(id = 63)
    private BooleanParam approved;
    @AttrId(id = 68)
    private StringParam image;
    @AttrId(id = 70)
    private StringParam manager;
    @AttrId(id = 71)
    private StringParam type;
    @AttrId(id = 72)
    private StringParam activity;

    public Event() {
    }

    public Event(String message) {
        super(message);
    }

    public List<Long> getTags() {
        if(tags != null)
            return tags.get();
        return null;
    }

    public void setTags(List<Long> tags) {
        if(this.tags == null)
            this.tags = new ListParam(tags);
        this.tags.set(tags);
    }

    public Long getCompany() {
        if(company != null)
            return company.get();
        return null;
    }

    public void setCompany(Long company) {
        if(this.company == null)
            this.company = new LongParam(company);
        this.company.set(company);
    }

    public String getTitle() {
        if(title != null)
            return title.get();
        return null;
    }

    public void setTitle(String title) {
        if(this.title == null)
            this.title = new StringParam(title);
        this.title.set(title);
    }

    public String getDescriptionShort() {
        if(descriptionShort != null)
            return descriptionShort.get();
        return null;
    }

    public void setDescriptionShort(String descriptionShort) {
        if(this.descriptionShort == null)
            this.descriptionShort = new StringParam(descriptionShort);
        this.descriptionShort.set(descriptionShort);
    }

    public String getDescription() {
        if(description != null)
            return description.get();
        return null;
    }

    public void setDescription(String description) {
        if(this.description == null)
            this.description = new StringParam(description);
        this.description.set(description);
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getStartDate() {
        if(startDate != null)
            return startDate.get();
        return null;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public void setStartDate(Date startDate) {
        if(this.startDate == null && startDate != null)
            this.startDate = new DateParam(startDate);
        else
            this.startDate.set(startDate);
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getEndDate() {
        if(endDate != null)
            return endDate.get();
        return null;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public void setEndDate(Date endDate) {
        if(this.endDate == null && endDate != null)
            this.endDate = new DateParam(endDate);
        else
            this.endDate.set(endDate);
    }

    @JsonSerialize(using = TimeSerializer.class)
    public Date getStartTime() {
        if(startTime != null)
            return startTime.get();
        return null;
    }

    @JsonDeserialize(using = TimeDeserializer.class)
    public void setStartTime(Date startTime) {
        if(this.startTime == null && startTime != null)
            this.startTime = new TimeParam(startTime);
        else
            this.startTime.set(startTime);
    }

    @JsonSerialize(using = TimeSerializer.class)
    public Date getEndTime() {
        if(endTime != null)
            return endTime.get();
        return null;
    }

    @JsonDeserialize(using = TimeDeserializer.class)
    public void setEndTime(Date endTime) {
        if(this.endTime == null && endTime != null)
            this.endTime = new TimeParam(endTime);
        else
            this.endTime.set(endTime);
    }

    public Boolean getApproved() {
        if(approved != null)
            return approved.get();
        return null;
    }

    public void setApproved(Boolean approved) {
        if(this.approved == null && approved != null)
            this.approved = new BooleanParam(approved);
        else
            this.approved.set(approved);
    }

    public String getImage() {
        if (image != null){
            return image.get();
        }
        return null;
    }

    public void setImage(String image) {
        if (this.image == null && image != null){
            this.image = new StringParam(image);
        } else {
            this.image.set(image);
        }
    }
    public String getManager() {
        if(manager != null)
            return manager.get();
        return null;
    }

    public void setManager(String manager) {
        if(this.manager == null && manager !=null)
            this.manager = new StringParam(manager);
        this.manager.set(manager);
    }

    public String getType() {
        if(type != null)
            return type.get();
        return null;
    }

    public void setType(String type) {
        if(this.type == null && type !=null)
            this.type = new StringParam(type);
        this.type.set(type);
    }

    public String getActivity() {
        if(activity != null)
            return activity.get();
        return null;
    }

    public void setActivity(String activity) {
        if(this.activity == null && activity !=null)
            this.activity = new StringParam(activity);
        this.activity.set(activity);
    }

    @Override
    public String toString() {
        return "Event{" +
                "tags=" + tags +
                ", company=" + company +
                ", title='" + title + '\'' +
                ", descriptionShort='" + descriptionShort + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
