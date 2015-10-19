package ua.com.studhero.database.entities;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Eugene on 19.09.2015.
 */
public class SearchScope {
    private List<String> paramNames;
    private String paramValue;
    private List<Long> paramAttrIds;
    private List<Long> textAttrIds;

    public SearchScope(){
    }

    public SearchScope(String paramValue, List<Long> paramAttrIds) {
        this.paramValue = paramValue;
        this.paramAttrIds = paramAttrIds;
    }

    public List<String> getParamNames() {
        return paramNames;
    }

    public void setParamNames(List<String> paramNames) {
        this.paramNames = paramNames;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public List<Long> getParamAttrIds() {
        return paramAttrIds;
    }

    public void setParamAttrIds(List<Long> paramAttrIds) {
        this.paramAttrIds = paramAttrIds;
    }

    public void setTextAttrIds(List<Long> textAttrIds) {
        this.textAttrIds = textAttrIds;
    }

    public List<Long> getTextAttrIds() {
        return textAttrIds;
    }

    @Override
    public String toString() {
        return "SearchScope{" +
                "paramNames=" + paramNames +
                ", paramValue='" + paramValue + '\'' +
                ", paramAttrIds=" + paramAttrIds +
                ", textAttrIds=" + textAttrIds +
                '}';
    }
}
