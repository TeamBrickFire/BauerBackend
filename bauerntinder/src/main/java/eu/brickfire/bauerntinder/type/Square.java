package eu.brickfire.bauerntinder.type;

import org.json.simple.JSONObject;

import java.io.Serializable;

public class Square implements Serializable {
    private String id;
    private String fieldId;
    private int x;
    private int y;
    private boolean isBlocked;

    public Square(String id, String fieldId, int x, int y, boolean isBlocked) {
        this.id = id;
        this.fieldId = fieldId;
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
    }

    public Square(String id, int x, int y, boolean isBlocked) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
    }

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("field_id", fieldId);
        json.put("x", x);
        json.put("y", y);
        json.put("blocked", isBlocked);
        return json;
    }
}
