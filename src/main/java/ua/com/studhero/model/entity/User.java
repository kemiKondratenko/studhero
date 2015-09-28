package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.StringParam;

/**
 * Created by kaspyar on 9/9/15.
 */
@ClassId(id = 6)
public class User extends BaseDBO{

    @AttrId(id = 32)
    private StringParam email;
    @AttrId(id = 33)
    private StringParam password;

    public User() {
    }

    public String getEmail() {
        if(email != null)
            return email.get();
        return null;
    }

    public void setEmail(String email) {
        if(this.email == null && email !=null)
            this.email = new StringParam(email);
        this.email.set(email);
    }

    public String getPassword() {
        if(password != null)
            return password.get();
        return null;
    }

    public void setPassword(String password) {
        if(this.password == null && password != null)
            this.password = new StringParam(password);
        this.password.set(password);
    }
}
