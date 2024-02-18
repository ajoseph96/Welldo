package model;

import java.util.ArrayList;

//superclass implementing methods changing various task lists
public class Tasks {

    protected ArrayList<String> taskList;


    // EFFECTS: constructor for Task Class.
    public Tasks() {
        taskList = new ArrayList<String>();

    }

    public ArrayList<String> getTaskList() {
        return taskList;
    }

    public void addTaskList(String s) {
        taskList.add(s);
    }
}
