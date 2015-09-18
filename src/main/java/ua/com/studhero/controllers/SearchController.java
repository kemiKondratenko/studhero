package ua.com.studhero.controllers;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;
import ua.com.studhero.services.SearchService;
import ua.com.studhero.services.impl.SearchServiceImpl;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 18.09.2015.
 */
@Controller
@RequestMapping("search")
public class SearchController {

    Logger log = Logger.getLogger("Logger");

    @Inject
    private SearchServiceImpl searchService;

    @RequestMapping(value="/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<BaseDBO> search(@RequestBody List<SearchScope> searchScopeList){
        try {
            return searchService.search(Lists.newArrayList(searchService.transform(searchScopeList)));
        } catch (Exception e) {
            return Collections.singletonList(new BaseDBO(e.toString()));
        }
    }
}
