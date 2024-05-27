package model;

import org.json.JSONArray;
import org.json.JSONObject;

//extension of task class, containing a list of tasks related to the emotion of sadness
public class SadTasks extends Tasks {

    // EFFECTS: constructor for SadTasks Class.
    public SadTasks() {
        super();
        taskList.add("journaling");
    }
}
