package ua.com.studhero.controllers.entity;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.model.entity.Tag;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kaspyar on 9/17/15.
 */
@Controller
@RequestMapping("restTags")
public class TagsController {
    @Inject
    private DataBaseWorker dataBaseWorker;

    @RequestMapping(value="/getTags", method = RequestMethod.GET)
    public @ResponseBody
    List<Tag> getTags(){
        try {
            return dataBaseWorker.get(Tag.class);
        } catch (Exception e) {
            return Lists.newArrayList(new Tag(e.getMessage()));
        }

    }

    @RequestMapping(value="/createTag", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Tag createTag(@RequestBody Tag tag){
        try{
            return dataBaseWorker.get(dataBaseWorker.save(tag), tag.getClass());
        }
        catch (Exception e){
            return new Tag(e.getMessage());
        }

    }
}
