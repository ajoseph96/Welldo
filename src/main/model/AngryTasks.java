package model;

import org.json.JSONArray;
import org.json.JSONObject;

//extension of task class, containing a list of tasks related to the emotion of anger
public class AngryTasks extends Tasks {


    // EFFECTS: constructor for AngryTasks Class.
    public AngryTasks() {
        super();
        taskList.add("30-minute jog");
    }

}
