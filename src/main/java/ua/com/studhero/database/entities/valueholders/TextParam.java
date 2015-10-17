package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.constants.AttrTypes;
import ua.com.studhero.database.entities.valueholders.base.Param;


/**
 * Created by Eugene on 17.10.2015.
 */
public class TextParam  extends Param<TextValue> {

    public static final long TEXTPARAM = AttrTypes.Text;

    public TextParam(TextValue value) {
        super(0, value);
    }


    public TextParam(long param_id, TextValue value) {
        super(param_id, value);
    }

    @Override
    public TextValue get() {
        return super.get();
    }
}
