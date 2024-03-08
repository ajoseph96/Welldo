package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//superclass implementing methods changing various task lists
public class Tasks implements Writable {

    protected ArrayList<String> taskList;


    // EFFECTS: constructor for Task Class.
    public Tasks() {
        taskList = new ArrayList<String>();

    }

    public ArrayList<String> getTaskList() {
        return taskList;
    }


    // MODIFIES: this
    // EFFECTS: adds task to task list
    public void addTaskList(String s) {
        taskList.add(s);
    }

    // EFFECTS: returns size of task list
    public int getTaskListSize() {
        return taskList.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskList", taskListToJson());
        return json;
    }

    // EFFECTS: returns tasks in task list as a JSON array
    public JSONArray taskListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : taskList) {
            jsonArray.put(stringtoJson(s));
        }

        return jsonArray;
    }

    // EFFECTS: returns tasks in task list as a JSON array
    public JSONObject stringtoJson(String s) {
        JSONObject json = new JSONObject();
        json.put("string", s);
        return json;
    }

}
