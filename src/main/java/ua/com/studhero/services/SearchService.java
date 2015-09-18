package ua.com.studhero.services;

import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.SearchScope;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eugene on 19.09.2015.
 */
public interface SearchService {
    List<BaseDBO> search(List<SearchScope> searchScopeList) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    Iterable<SearchScope> transform(List<SearchScope> searchScopeList);
}
