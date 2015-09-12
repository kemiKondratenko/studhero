package ua.com.studhero.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.constants.ClassFactory;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.LoginValuesUser;
import ua.com.studhero.database.impl.DataBaseWorkerMock;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Event;
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
    private DataBaseWorkerMock dataBaseWorkerMock = new DataBaseWorkerMock();

    @RequestMapping(value="/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    BaseDBO login(@RequestBody LoginValuesUser login){
        BaseDBO res = new BaseDBO();
        res.setObjectId(465465465465l);
        try {
            long id =  dataBaseWorker.getIdIfExists(login.getUser());
            if(id != 0){
                log.info("Prim " + dataBaseWorker.getPrimaryClassId(id)+ " for "+ id);
                Class primary = ClassFactory.getClassById(dataBaseWorker.getPrimaryClassId(id));
                res = dataBaseWorker.get(id, primary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}
