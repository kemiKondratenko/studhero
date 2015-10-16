package ua.com.studhero.database.entities;

import ua.com.studhero.annotations.ClassId;

/**
 * Created by Eugene on 06.09.2015.
 */

@ClassId( id = 1)
public class BaseDBO {
    private long objectId;

    private String error;

    public BaseDBO() {

    }

    public BaseDBO(String error){this.error = error;}

    public BaseDBO(Long objectId) {
        this.objectId = objectId;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
