package ua.com.studhero.database.entities;

import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.annotations.AttrId;

/**
 * Created by Eugene on 08.09.2015.
 */
@ClassId(id = 2)
public class Example extends BaseDBO{

    @AttrId( id = 1)
    private int field;
}
