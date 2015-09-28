package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.StringParam;

/**
 * Created by kaspyar on 9/9/15.
 */
@ClassId(id = 8)
public class Company extends BaseDBO{
    @AttrId(id = 34)
    private StringParam name;
    @AttrId(id = 35)
    private StringParam status;

    public Company() {
    }

    public Company(String message) {
        super(message);
    }

    public String getStatus() {
        if(status != null)
            return status.get();
        return null;
    }

    public void setStatus(String status) {
        if(this.status == null && status !=null)
            this.status = new StringParam(status);
        this.status.set(status);
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
}
