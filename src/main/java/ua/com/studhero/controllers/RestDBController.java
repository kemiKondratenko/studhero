package ua.com.studhero.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.constants.ClassFactory;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.model.entity.User;

import javax.inject.Inject;
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
            long id =  dataBaseWorker.getIdIfExists(entity);
            if(id != 0){
                log.info("Prim " + dataBaseWorker.getPrimaryClassId(id)+ " for "+ id);
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
            return dataBaseWorker.createLoginable(user.getEmail(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
