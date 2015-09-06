package ua.com.studhero.database.entities;

import ua.com.studhero.annotations.ClassId;

/**
 * Created by Eugene on 06.09.2015.
 */

@ClassId( id = 1)
public class BaseDBO {
    private long objectId;

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
}
