package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// This code is modeled after JsonSerializationDemo from the Phase 2 demo, written by Paul Carter.
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
