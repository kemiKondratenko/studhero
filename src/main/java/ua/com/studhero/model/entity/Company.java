package ua.com.studhero.model.entity;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.StringParam;

/**
 * Created by kaspyar on 9/9/15.
 */
@ClassId(id = 8)
public class Company extends BaseDBO{
    @AttrId(id = 34)
    private StringParam name;
    @AttrId(id = 35)
    private StringParam email;
    @AttrId(id = 64)
    private StringParam phone;
    @AttrId(id = 65)
    private StringParam edrpoy;

    @AttrId(id = 66)
    private StringParam city;
    @AttrId(id = 67)
    private StringParam activity;

    public Company() {
    }

    public Company(String message) {
        super(message);
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


    public String getName() {
        if(name != null)
            return name.get();
        return null;
    }

    public void setName(String name) {
        if(this.name == null && name !=null)
            this.name = new StringParam(name);
        this.name.set(name);
    }

    public String getPhone() {
        if(phone != null)
            return phone.get();
        return null;
    }

    public void setPhone(String phone) {
        if(this.phone == null && phone !=null)
            this.phone = new StringParam(phone);
        this.phone.set(phone);
    }
    public String getEdrpoy() {
        if(edrpoy != null)
            return edrpoy.get();
        return null;
    }

    public void setEdrpoy(String edrpoy) {
        if(this.edrpoy == null && edrpoy !=null)
            this.edrpoy = new StringParam(edrpoy);
        this.edrpoy.set(edrpoy);
    }

    public String getCity() {
        if(city != null)
            return city.get();
        return null;
    }

    public void setCity(String city) {
        if(this.city == null && city !=null)
            this.city = new StringParam(city);
        this.city.set(city);
    }

    public String getActivity() {
        if(activity != null)
            return activity.get();
        return null;
    }

    public void setActivity(String activity) {
        if(this.activity == null && activity !=null)
            this.activity = new StringParam(activity);
        this.activity.set(activity);
    }
}
