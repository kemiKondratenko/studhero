package ua.com.studhero.services.impl;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.constants.AttrIdFactory;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;
import ua.com.studhero.services.SearchService;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 19.09.2015.
 */
public class SearchServiceImpl implements SearchService {

    Logger log = Logger.getLogger("Logger");


    private DataBaseWorker dataBaseWorker;

    @Override
    public List<BaseDBO> search(List<SearchScope> searchScopeList) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Long> resultIds = Lists.newArrayList();
        for(SearchScope searchScope: searchScopeList){
            resultIds = filter(resultIds, dataBaseWorker.search(searchScope));
        }
        List<BaseDBO> resultObjects = Lists.newArrayList();
        for(Long id : resultIds){
            resultObjects.add(dataBaseWorker.get(id));
        }
        return resultObjects;
    }

    @Override
    public List<BaseDBO> search(SearchScope searchScope) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Long> resultIds = Lists.newArrayList();
        resultIds = filter(resultIds, dataBaseWorker.search(searchScope));
        List<BaseDBO> resultObjects = Lists.newArrayList();
        for(Long id : resultIds){
            resultObjects.add(dataBaseWorker.get(id));
        }
        return resultObjects;
    }

    @Override
    public Iterable<SearchScope> transform(List<SearchScope> searchScopeList) {
        return Iterables.transform(searchScopeList, new Function<SearchScope, SearchScope>() {
            @Override
            public SearchScope apply(SearchScope searchScope) {
                if(searchScope.getParamAttrId() == 0)
                    try {
                        searchScope.setParamAttrId(AttrIdFactory.getAttrId(searchScope.getParamName()));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                return searchScope;
            }
        });
    }

    private List<Long> filter(List<Long> resultIds, final List<Long> search){
        log.info(search.toString());
        if(resultIds.isEmpty()){
            resultIds.addAll(search);
            return resultIds;
        }
        resultIds = Lists.newArrayList(Iterables.filter(resultIds, new Predicate<Long>() {
            @Override
            public boolean apply(Long object_id) {
                return search.contains(object_id);
            }
        }));
        return resultIds;
    }

    public void setDataBaseWorker(DataBaseWorker dataBaseWorker) {
        this.dataBaseWorker = dataBaseWorker;
    }

}
