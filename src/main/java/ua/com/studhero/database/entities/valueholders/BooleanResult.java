package ua.com.studhero.database.entities.valueholders;

import ua.com.studhero.database.entities.BaseDBO;

/**
 * @author kaspyar.
 */
public class BooleanResult extends BaseDBO {
    boolean result;

    public BooleanResult() {
    }

    public BooleanResult(boolean result) {
        this.result = result;
    }

    public BooleanResult(String error) {
        super(error);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
