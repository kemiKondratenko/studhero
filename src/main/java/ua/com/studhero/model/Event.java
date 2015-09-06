package ua.com.studhero.model;

/**
 * Created by kaspyar on 9/6/15.
 */
public class Event {
    String name;
    String staffName[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStaffName() {
        return staffName;
    }

    public void setStaffName(String[] staffName) {
        this.staffName = staffName;
    }
}
