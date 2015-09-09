package ua.com.studhero.model.entity;

import ua.com.studhero.database.entities.BaseDBO;

/**
 * Created by kaspyar on 9/9/15.
 */
public class Company extends BaseDBO{
    private String name;
    private String status;

    public Company() {
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
