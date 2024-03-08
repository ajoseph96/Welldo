package model;

import org.json.JSONArray;
import org.json.JSONObject;

//extension of task class, containing a list of tasks related to the emotion of happiness
public class HappyTasks extends Tasks {

    // EFFECTS: constructor for HappyTasks Class.
    public HappyTasks() {
        super();
        taskList.add("10-minute meditation");
    }


}
