package ua.com.studhero.model.entity;

import ua.com.studhero.database.entities.BaseDBO;

/**
 * @author - KaspYar
 */
public class Tag extends BaseDBO{
    private String tag;

    public Tag() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
