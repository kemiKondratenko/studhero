package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;

/**
 * Created by kaspyar on 9/9/15.
 */
@ClassId(id = 6)
public class User extends BaseDBO{

    @AttrId(id = 32)
    private String email;
    @AttrId(id = 33)
    private String password;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
