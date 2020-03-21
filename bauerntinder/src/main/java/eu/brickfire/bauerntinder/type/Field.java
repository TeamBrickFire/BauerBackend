package eu.brickfire.bauerntinder.type;

import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Field implements Serializable {
    private String id;
    private String farmerId;
    private List<Square> squares;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("farmerId", farmerId);
        return json;
    }
}
