package ua.com.studhero.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.constants.ClassFactory;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.model.entity.Tag;
import ua.com.studhero.model.entity.User;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 08.09.2015.
 */
@Controller
@RequestMapping("db")
public class RestDBController {

    Logger log = Logger.getLogger("Logger");

    @Inject
    private DataBaseWorker dataBaseWorker;

    @RequestMapping(value="/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    BaseDBO login(@RequestBody User entity){
        BaseDBO res = new BaseDBO();
        try {
            log.info("Income");
            log.info("login: " + entity.getEmail());
            log.info("pswd; "  + entity.getPassword());
            long id =  dataBaseWorker.getIdIfExists(entity);
            log.info("id(if exist): "+ id);
            if(id != 0){
                log.info("Prim " + dataBaseWorker.getPrimaryClassId(id) + " for " + id);
                Class primary = ClassFactory.getClassById(dataBaseWorker.getPrimaryClassId(id));
                res = dataBaseWorker.get(id, primary);
            }
        } catch (Exception e) {
            return new BaseDBO(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value="/registrate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    long registrate(@RequestBody User user){
        try {
            log.info("email: "+user.getEmail());
            log.info("pswd: "+user.getPassword());
            return dataBaseWorker.createLoginable(user.getEmail(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @RequestMapping(value="/getTags", method = RequestMethod.GET)
    public @ResponseBody
    List<Tag> getTags(){
        try {
            return dataBaseWorker.get(Tag.class);
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

    @RequestMapping(value="/createTag", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    BaseDBO createTag(@RequestBody Tag tag){
        try{
            return dataBaseWorker.get(dataBaseWorker.save(tag), Tag.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new BaseDBO("Can't create tag");
    }

    @RequestMapping(value="validateLogin/{login}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    boolean isLoginValid(@PathVariable String login){
        try {
            return dataBaseWorker.isLoginValid(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
