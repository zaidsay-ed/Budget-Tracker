package persistance;

import org.json.JSONObject;

public interface Writable {
//interface for returning as JSON Object
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
