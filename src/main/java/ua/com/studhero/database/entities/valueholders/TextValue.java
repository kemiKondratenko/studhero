package ua.com.studhero.database.entities.valueholders;

/**
 * Created by Eugene on 17.10.2015.
 */
public class TextValue {
    private Long id;
    private String value;

    public TextValue(Long id) {
        this.id = id;
    }

    public TextValue(String value) {
        this.value = value;
    }

    public TextValue(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TextValue{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
