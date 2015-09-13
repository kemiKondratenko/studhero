package ua.com.studhero.database.entities;

import ua.com.studhero.model.entity.User;

/**
 * Created by Eugene on 13.09.2015.
 */
public class LoginValuesHolder {
    User user;
    BaseDBO userInfo;

    public User getUser() {
        return user;
    }

    public BaseDBO getUserInfo() {
        return userInfo;
    }

}
