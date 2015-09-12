package ua.com.studhero.controllers.entity;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Event;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 13.09.2015.
 */
@Controller
@RequestMapping("companies")
public class CompaniesController {

    @Inject
    private DataBaseWorker dataBaseWorker;


    @RequestMapping(value="/{id:.+}", method = RequestMethod.GET)
    public @ResponseBody
    List<Company> get(@PathVariable long id){
        try {
           /* if(id != 0)
                return dataBaseWorker.get(id, Company.class);*/
            return dataBaseWorker.get(Company.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/create", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Company create(@RequestBody Company entity){
        try {
            return dataBaseWorker.get(dataBaseWorker.save(entity), entity.getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}
