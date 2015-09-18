package ua.com.studhero.database.entities;

/**
 * Created by Eugene on 19.09.2015.
 */
public class SearchScope {
    private String paramName;
    private String paramValue;
    private long paramAttrId = 0;

    public String getParamName() {
        return paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public long getParamAttrId() {
        return paramAttrId;
    }

    public void setParamAttrId(long paramAttrId) {
        this.paramAttrId = paramAttrId;
    }
}
