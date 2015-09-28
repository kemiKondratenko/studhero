package ua.com.studhero.controllers.entity.model;

import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.User;

/**
 * Created by Eugene on 29.09.2015.
 */
public class CompanyRegistrateModel {
    private User user;
    private Company company;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
