package ua.com.studhero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.Example;
import ua.com.studhero.database.impl.ConnectorImpl;
import ua.com.studhero.database.impl.DataBaseWorkerImpl;

import javax.inject.Inject;
import java.sql.SQLException;
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
    String getFromDB(){
        /*ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DataBaseWorker merchantService = context.getBean(DataBaseWorkerImpl.class);*/
        log.info("START");
        try {
            return " "+dataBaseWorker.get(2l, Example.class).field;
        } catch (Exception e) {
            return " ";
        }
    }

    public void setDataBaseWorker(DataBaseWorker dataBaseWorker) {
        this.dataBaseWorker = dataBaseWorker;
    }
}
