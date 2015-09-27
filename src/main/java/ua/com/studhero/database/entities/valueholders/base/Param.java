package ua.com.studhero.database.entities.valueholders.base;

/**
 * Created by Eugene on 06.09.2015.
 */
public class Param<T> {

    private final long param_id;
    private T value;

    public Param(long param_id, T value) {
        this.param_id = param_id;
        this.value = value;
    }

    public T get(){
        return value;
    }

    public long getId() {
        return param_id;
    }

    public Object getBaseForm() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Param)) return false;

        Param param = (Param) o;

        if (param_id != param.param_id) return false;
        if (!value.equals(param.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (param_id ^ (param_id >>> 32));
        result = value != null ? 31 * result + value.hashCode() : 31 * result;
        return result;
    }

    public void set(T value) {
        this.value = value;
    }
}
