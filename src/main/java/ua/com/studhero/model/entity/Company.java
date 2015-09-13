package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;

/**
 * Created by kaspyar on 9/9/15.
 */
@ClassId(id = 8)
public class Company extends BaseDBO{
    @AttrId(id = 34)
    private String name;
    @AttrId(id = 35)
    private String status;

    public Company() {
    }

    public Company(String message) {
        super(message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
