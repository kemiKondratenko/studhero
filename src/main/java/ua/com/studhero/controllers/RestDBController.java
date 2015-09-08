package ua.com.studhero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.Example;

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

    @RequestMapping(value="/getFromDB", method = RequestMethod.GET)
    public @ResponseBody
    Example getFromDB(){
        try {
            return dataBaseWorker.get(2l, Example.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDataBaseWorker(DataBaseWorker dataBaseWorker) {
        this.dataBaseWorker = dataBaseWorker;
    }
}
