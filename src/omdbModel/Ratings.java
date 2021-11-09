package omdbModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ratings {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;

    public void setSource(String source) {
        this.source = source;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }
}
