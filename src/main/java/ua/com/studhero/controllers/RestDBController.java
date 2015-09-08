package ua.com.studhero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.Example;

import java.sql.SQLException;

/**
 * Created by Eugene on 08.09.2015.
 */
@Controller
@RequestMapping("db")
public class RestDBController {

    private DataBaseWorker dataBaseWorker;

    @RequestMapping(value="/getFromDB", method = RequestMethod.GET)
    public @ResponseBody
    Example getFromDB() throws IllegalAccessException, SQLException, InstantiationException {
        try {
            return dataBaseWorker.get(2l, Example.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDataBaseWorker(DataBaseWorker dataBaseWorker) {
        this.dataBaseWorker = dataBaseWorker;
    }
}
