package ua.com.studhero.controllers.entity;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.constants.Attrs;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;
import ua.com.studhero.database.entities.valueholders.BooleanParam;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.services.impl.SearchServiceImpl;

import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 13.09.2015.
 */
@Controller
@RequestMapping("events")
public class EventsController {

    Logger log = Logger.getLogger("Logger");

    @Inject
    private DataBaseWorker dataBaseWorker;

    @Inject
    private SearchServiceImpl searchService;


    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> get(){
        try {
            return dataBaseWorker.get(Event.class);
        }  catch (Exception e) {
            return Lists.newArrayList(new Event(e.getMessage()));
        }
    }

    @RequestMapping(value="/limit", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> limit(@RequestParam long from, @RequestParam long to){
        try {
            if(from == 0 && to == 0)
                return Lists.newArrayList(new Event("Parameters were incorrect"));
            else
                return dataBaseWorker.get(Event.class, from, to);
        }  catch (Exception e) {
            return Lists.newArrayList(new Event(e.getMessage()));
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Event getById(@PathVariable long id){
        try {
            return dataBaseWorker.get(id, Event.class);
        }  catch (Exception e) {
            return new Event(e.getMessage());
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Event create(@RequestBody Event entity) {
        log.info("FIN"+ entity.toString());
        try {
            log.info("In");
            if(entity.getObjectId() == 0) {
                log.info("no id");
                return dataBaseWorker.get(dataBaseWorker.save(entity), entity.getClass());
            }else {
                log.info("with id");
                return dataBaseWorker.get(dataBaseWorker.save(entity.getObjectId(), entity), entity.getClass());
            }
        }  catch (Exception e) {
            return new Event(e.getMessage());
        }
    }


    @RequestMapping(value = "/approved", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<BaseDBO> approved() {
        try {
            return searchService.search(new SearchScope(String.valueOf(BooleanParam.TRUE), Attrs.Approved));
        }  catch (Exception e) {
            return Lists.newArrayList(new BaseDBO(e.getMessage()));
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File("/home/h106815/tmpFiles");
                log.info("path: "+ dir.getAbsolutePath());
                log.info("can write?: "+dir.canWrite());
                dir.setWritable(true);
                log.info("can write?: "+dir.canWrite());
                dir.setWritable(true, true);
                log.info("can write?: "+dir.canWrite());
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                log.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name
                    + " because the file was empty.";
        }
    }

}
