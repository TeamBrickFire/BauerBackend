package eu.brickfire.bauerntinder.type;

import org.json.simple.JSONObject;

import java.io.Serializable;

public class Square implements Serializable {
    private String id;
    private String fieldId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("fieldId", fieldId);
        return json;
    }
}
