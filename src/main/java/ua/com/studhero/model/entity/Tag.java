package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;

/**
 * @author - KaspYar
 */
@ClassId(id = 10)
public class Tag extends BaseDBO{
    @AttrId(id = 45)
    private String tag;

    public Tag() {
    }
    public Tag(String msg){
        super(msg);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
