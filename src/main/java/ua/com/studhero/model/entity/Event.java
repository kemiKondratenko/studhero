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
    private IdListParam tags;
    @AttrId(id = 39)
    private StringParam company;
    @AttrId(id = 34)
    private StringParam title;
    @AttrId(id = 40)
    private TextParam descriptionShort;
    @AttrId(id = 41)
    private TextParam description;
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

    public Event(Long id) {
        super(id);
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
        if(tags ==null) return;
        if(this.tags == null)
            this.tags = new IdListParam(tags);
        else
            this.tags.set(tags);
    }

    public String getCompany() {
        if(company != null)
            return company.get();
        return null;
    }

    public void setCompany(String company) {
        if(company ==null) return;
        if(this.company == null)
            this.company = new StringParam(company);
        else
            this.company.set(company);
    }

    public String getTitle() {
        if(title != null)
            return title.get();
        return null;
    }

    public void setTitle(String title) {
        if(title ==null) return;
        if(this.title == null)
            this.title = new StringParam(title);
        else
            this.title.set(title);
    }

    public String getDescriptionShort() {
        if(descriptionShort != null)
            return descriptionShort.get().getValue();
        return null;
    }

    public void setDescriptionShort(String descriptionShort) {
        if(descriptionShort ==null) return;
        if(this.descriptionShort == null)
            this.descriptionShort = new TextParam(new TextValue(descriptionShort));
        else
            this.descriptionShort.get().setValue(descriptionShort);
    }

    public String getDescription() {
        if(description != null)
            return description.get().getValue();
        return null;
    }

    public void setDescription(String description) {
        if(description ==null) return;
        if(this.description == null)
            this.description = new TextParam(new TextValue(description));
        else
            this.description.get().setValue(description);
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getStartDate() {
        if(startDate != null)
            return startDate.get();
        return null;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public void setStartDate(Date startDate) {
        if(startDate ==null) return;
        if(this.startDate == null)
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
        if(endDate ==null) return;
        if(this.endDate == null)
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
        if(startTime ==null) return;
        if(this.startTime == null)
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
        if(endTime ==null) return;
        if(this.endTime == null)
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
        if(approved ==null) return;
        if(this.approved == null)
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
        if(image ==null) return;
        if (this.image == null){
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
        if(manager ==null) return;
        if(this.manager == null)
            this.manager = new StringParam(manager);
        else
            this.manager.set(manager);
    }

    public String getType() {
        if(type != null)
            return type.get();
        return null;
    }

    public void setType(String type) {
        if(type ==null) return;
        if(this.type == null)
            this.type = new StringParam(type);
        else
            this.type.set(type);
    }

    public String getActivity() {
        if(activity != null)
            return activity.get();
        return null;
    }

    public void setActivity(String activity) {
        if(activity ==null) return;
        if(this.activity == null)
            this.activity = new StringParam(activity);
        else
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
