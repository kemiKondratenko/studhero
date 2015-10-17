package ua.com.studhero.controllers.entity;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.controllers.entity.model.CompanyRegistrateModel;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.mail.Emailer;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eugene on 13.09.2015.
 */
@Controller
@RequestMapping("companies")
public class CompaniesController {

    @Inject
    private DataBaseWorker dataBaseWorker;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Company> get() {
        try {
            return dataBaseWorker.get(Company.class);
        } catch (Exception e) {
            return Lists.newArrayList(new Company(e.getMessage()));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Company getById(@PathVariable long id) {
        try {
            return dataBaseWorker.get(id, Company.class);
        } catch (Exception e) {
            return new Company(e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Company update(@RequestBody Company entity) {
        try {
            if (entity.getObjectId() == 0) {
                return new Company("Object does not have an id");
            }
            return dataBaseWorker.get(dataBaseWorker.save(entity.getObjectId(), entity), entity.getClass());
        } catch (Exception e) {
            return new Company(e.getMessage());
        }
    }


    @RequestMapping(value = "/registrate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Company registrate(@RequestBody CompanyRegistrateModel entity) {
        try {
            long newId = dataBaseWorker.createLoginable(entity.getUser().getEmail(), entity.getUser().getPassword());
            if (newId != 0) {
                dataBaseWorker.save(newId, entity.getCompany());
                Emailer.sendRegistrationEmailToCompany(entity.getUser().getEmail(),
                        entity.getCompany().getName(),
                        entity.getUser().getPassword());
                return dataBaseWorker.get(newId, Company.class);
            } else
                return new Company("cant create company");
        } catch (Exception e) {
            return new Company(e.getMessage());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Company login(@RequestBody User entity) {
        try {
            long id = dataBaseWorker.getIdIfExists(entity);
            long classId_id = dataBaseWorker.getPrimaryClassId(id);
            long company_classId_id = Company.class.getAnnotation(ClassId.class).id();
            if (id == 0 || classId_id != company_classId_id) return new Company("No such company");
            return dataBaseWorker.get(id, Company.class);
        } catch (Exception e) {
            return new Company(e.getMessage());
        }
    }
}
