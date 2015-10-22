package ua.com.studhero.controllers.entity;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.studhero.Key;
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
            List<Event> lst = dataBaseWorker.get(Event.class);
            for (Event event:lst){
                if (event.getImage()!=null){
                    event.setImage(Key.SERVER_PATH+Key.IMAGES_EVENTS_DIR+event.getImage());
                }
            }
            return lst;
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
                entity.setApproved(false);
                return dataBaseWorker.get(dataBaseWorker.save(entity), entity.getClass());
            }else {
                return new Event("Event has an id, do you want to create or update?");
            }
        }  catch (Exception e) {
            return new Event(e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Event update(@RequestBody Event entity) {
        log.info("FIN"+ entity.toString());
        try {
            log.info("In");
            if(entity.getObjectId() == 0) {
                return new Event("Event does not have an id, cant update it");
            }else {
                log.info("with id" + entity.getObjectId());
                log.info("approved: " + entity.getApproved());
                if (entity.getImage() != null) {
                    log.info("image not null: " + entity.getImage());
                    entity.setImage(entity.getImage().substring(entity.getImage().lastIndexOf("/") + 1));
                } else {
                    log.info("image null");
                }
                dataBaseWorker.update(entity);
                return dataBaseWorker.get(entity.getObjectId(), entity.getClass());
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
            return searchService.search(new SearchScope(String.valueOf(BooleanParam.TRUE), Lists.newArrayList(Attrs.Approved)));
        }  catch (Exception e) {
            return Lists.newArrayList(new BaseDBO(e.getMessage()));
        }
    }


    @RequestMapping(value = "/unapproved", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<BaseDBO> unapproved() {
        try {
            return searchService.search(new SearchScope(String.valueOf(BooleanParam.FALSE), Lists.newArrayList(Attrs.Approved)));
        }  catch (Exception e) {
            return Lists.newArrayList(new BaseDBO(e.getMessage()));
        }
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<BaseDBO> search(@RequestBody List<SearchScope> searchScopeList) {
        try {
            log.info("Try Search");
            return searchService.search(searchService.transform(searchScopeList), Event.class);
        }  catch (Exception e) {
            return Lists.newArrayList(new BaseDBO(e.getMessage()));
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(Key.ROOT_DIR+Key.IMAGES_EVENTS_DIR);
                dir.setWritable(true);
                dir.setWritable(true, true);
                if (!dir.exists())
                    dir.mkdirs();

                String newName = String.valueOf(System.nanoTime());
                String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fullNewName = newName+extension;
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fullNewName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return fullNewName;
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName()
                    + " because the file was empty.";
        }
    }

}
